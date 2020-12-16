package com.gwy.manager.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gwy.manager.constant.PassRequestPaths;
import com.gwy.manager.constant.RoleName;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.SysLog;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.enums.ResponseStatus;
import com.gwy.manager.enums.SysLogType;
import com.gwy.manager.mapper.SysLogMapper;
import com.gwy.manager.rabbimq.RabbitmqProducer;
import com.gwy.manager.service.SysLogService;
import com.gwy.manager.util.DateUtilCustom;
import com.gwy.manager.util.PageHelperUtil;
import com.gwy.manager.util.ResultVOUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author Tracy
 * @date 2020/12/5 13:07
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    private static final String POST = "POST";

    @Autowired
    private SysLogMapper sysLogMapper;

    @Autowired
    private RabbitmqProducer producer;

    /**
     * 使用日志工具记录日志
     * @param request   请求
     * @param args  参数
     * @param resultVO  请求返回结果
     */
    @Override
    public void recordLog(HttpServletRequest request, Object[] args, ResultVO resultVO) {
        SysLog sysLog = new SysLog();

        String url = request.getRequestURL().toString();
        Locale locale = request.getLocale();

        sysLog.setRequestUrl(url);
        sysLog.setLocale(locale.toString());

        Authentication authentication = (Authentication) request.getUserPrincipal();
        if (authentication != null) {
            String userId = ((User) authentication.getPrincipal()).getUsername();
            sysLog.setUserId(userId);
            sysLog.setAuthorities(Arrays.toString(this.getRoleFromAuthorities(authentication.getAuthorities()).toArray()));
            sysLog.setIp(((WebAuthenticationDetails)authentication.getDetails()).getRemoteAddress());
        }

        sysLog.setParams(JSONObject.toJSONString(args));

        //设置返回信息
        sysLog.setResultMessage(resultVO.getMessage());

        //如果返回状态码为错误
        if (!resultVO.getResultCode().equals(ResponseStatus.SUCCESS.getCode())) {
            String errData = JSONObject.toJSONString(resultVO.getData().toString());
            errData = errData.substring(1, errData.length() - 1);

            //判断错误是否匹配
            boolean match = false;
            for (ResponseDataMsg value : ResponseDataMsg.values()) {
                if (errData.equals(value.getMsg())) {
                    match = true;
                    sysLog.setDataExplain(errData);
                    sysLog.setDataExplain(value.getExplain());
                    break;
                }
            }

            if (!match) {
                sysLog.setData(ResponseDataMsg.Fail.getMsg());
                sysLog.setDataExplain(ResponseDataMsg.Fail.getExplain());
            }
        } else {
            //设置状态码为成功
            sysLog.setData(ResponseDataMsg.Success.getMsg());
            sysLog.setDataExplain(ResponseDataMsg.Success.getExplain());
        }

        sysLog.setType(SysLogType.OperationLog.getType());
        sysLog.setTypeExplain(SysLogType.OperationLog.getTypeExplain());
        sysLog.setCreateTime(DateUtilCustom.getTime());

        producer.addLog(sysLog);
    }

    /**
     * 添加日志
     * @param sysLog    预添加的日志
     */
    @Override
    public void insertLog(SysLog sysLog) {
        sysLogMapper.insert(sysLog);
    }

    @Override
    public ResultVO getLogTypeAndCount() {

        List<Map<String, Object>> types = sysLogMapper.selectDataExplainAndCount();
        if (CollectionUtils.isEmpty(types)) {
            return ResultVOUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            return ResultVOUtil.success(types);
        }
    }

    @Override
    public ResultVO getLogInfoByType(String type, int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);

        List<SysLog> sysLogs = sysLogMapper.selectByType(type);
        if (CollectionUtils.isEmpty(sysLogs)) {
            return ResultVOUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            return ResultVOUtil.success(PageHelperUtil.pageInfoToMap(new PageInfo<>(sysLogs)));
        }

    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public ResultVO deleteByBatch(List<Integer> ids) {

        int i = sysLogMapper.deleteByPrimaryKeys(ids);
        if (i != ids.size()) {
            return ResultVOUtil.error(ResponseDataMsg.Fail.getMsg());
        }

        return ResultVOUtil.success(ResponseDataMsg.Success.getMsg());
    }

    @Override
    public ResultVO getLogByInterval(Date beginTime, Date endTime, String type) {

        List<SysLog> sysLogs = sysLogMapper.selectByInterval(beginTime, endTime, type);
        if (CollectionUtils.isEmpty(sysLogs)) {
            return ResultVOUtil.error(ResponseDataMsg.NotFound.getMsg());
        }

        return ResultVOUtil.success(sysLogs);
    }

    @Override
    public ResultVO getLogs(int pageNum, int pageSize) {

        List<SysLog> sysLogs = sysLogMapper.selectAll();
        if (CollectionUtils.isEmpty(sysLogs)) {
            return ResultVOUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            return ResultVOUtil.success(sysLogs);
        }
    }

    /**
     * 系统操作成功，添加日志
     * @param request   请求
     * @param authentication    认证信息
     */
    public void addLog(HttpServletRequest request, Authentication authentication) {
        this.addLog(request, authentication, null);
    }

    /**
     * 系统操作异常，添加日志
     * @param request   请求
     * @param ex    异常
     */
    public void addLog(HttpServletRequest request, Exception ex) {
        this.addLog(request, null, ex);
    }

    /**
     * 添加日志
     * @param request   本次请求
     * @param authentication    用户授权
     * @param ex    发生异常
     */
    private void addLog(HttpServletRequest request, Authentication authentication, Exception ex) {
        SysLog sysLog = new SysLog();

        sysLog.setRequestUrl(request.getRequestURL().toString());
        sysLog.setLocale(request.getLocale().toString());
        sysLog.setIp(request.getRemoteAddr());

        //默认请求成功
        sysLog.setResultMessage(ResponseStatus.SUCCESS.getMessage());
        sysLog.setData(ResponseDataMsg.Success.getMsg());
        sysLog.setDataExplain(ResponseDataMsg.Success.getExplain());

        //判断是否为登录请求
        if (sysLog.getRequestUrl().equals(PassRequestPaths.LOGIN_REQUEST) && request.getMethod().equals(POST)) {
            sysLog.setType(SysLogType.LoginLog.getType());
            sysLog.setTypeExplain(SysLogType.LoginLog.getTypeExplain());
        } else {
            sysLog.setType(SysLogType.OperationLog.getType());
            sysLog.setTypeExplain(SysLogType.OperationLog.getTypeExplain());
        }

        //如果有认证信息
        if (authentication != null) {
            sysLog.setUserId(((User) authentication.getPrincipal()).getUsername());
            sysLog.setAuthorities(Arrays.toString(this.getRoleFromAuthorities(authentication.getAuthorities()).toArray()));
        }

        //如果有访问异常
        if (ex != null) {
            sysLog.setResultMessage(ResponseStatus.FAIL.getMessage());
            ResponseDataMsg msg = ResponseDataMsg.valueOf(ex.getMessage());
            sysLog.setData(msg.getMsg());
            sysLog.setDataExplain(msg.getExplain());
            sysLog.setType(SysLogType.SystemLog.getType());
            sysLog.setTypeExplain(SysLogType.SystemLog.getTypeExplain());
        }

        sysLog.setCreateTime(DateUtilCustom.getTime());

        //生产者添加日志到消息队列
        producer.addLog(sysLog);
    }

    /**
     * 从权限、角色中提取出角色
     * @param authorities   权限+角色列表
     * @return  结果集
     */
    private List<GrantedAuthority> getRoleFromAuthorities(Collection<? extends GrantedAuthority> authorities) {
        //仅提取出用户的角色用作记录日志
        List<GrantedAuthority> authorityList = new ArrayList<>();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().startsWith(RoleName.ROLE_PREFIX)) {
                authorityList.add(authority);
            }
        }
        return authorityList;
    }
}
