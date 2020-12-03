package com.gwy.manager.util;

import com.alibaba.fastjson.JSONObject;
import com.gwy.manager.constant.RoleName;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.SysLog;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.enums.ResponseStatus;
import com.gwy.manager.mapper.SysLogMapper;
import com.gwy.manager.rabbimq.RabbitmqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author Tracy
 * @date 2020/11/29 19:52
 */
@Component
public class SysLogUtil {

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
    public void recordLog(HttpServletRequest request, Object[] args, ResultVO resultVO) {
        SysLog sysLog = new SysLog();
        Authentication authentication = (Authentication) request.getUserPrincipal();

        String user_id = ((User) authentication.getPrincipal()).getUsername();
        String url = request.getRequestURL().toString();
        Locale locale = request.getLocale();

        sysLog.setUserId(user_id);
        sysLog.setAuthorities(Arrays.toString(this.getRoleFromAuthorities(authentication.getAuthorities()).toArray()));
        sysLog.setRequestUrl(url);
        sysLog.setIp(((WebAuthenticationDetails)authentication.getDetails()).getRemoteAddress());
        sysLog.setParams(JSONObject.toJSONString(args));
        sysLog.setLocale(locale.toString());
        sysLog.setResultMessage(resultVO.getMessage());
        if (!resultVO.getResultCode().equals(ResponseStatus.SUCCESS.getCode())) {
            sysLog.setData(JSONObject.toJSONString(resultVO.getData().toString()));
        }
        sysLog.setCreateTime(DateUtilCustom.getTime());

        producer.addLog(sysLog);
    }

    public void addLog(HttpServletRequest request, Authentication authentication) {
        this.addLog(request, authentication, null);
    }

    public void addLog(HttpServletRequest request, AuthenticationException ex) {
        this.addLog(request, null, ex);
    }

    /**
     * 添加日志
     * @param request   本次请求
     * @param authentication    用户授权
     * @param ex    发生异常
     */
    private void addLog(HttpServletRequest request, Authentication authentication, AuthenticationException ex) {
        SysLog sysLog = new SysLog();

        sysLog.setRequestUrl(request.getRequestURL().toString());
        sysLog.setLocale(request.getLocale().toString());
        if (authentication != null) {
            sysLog.setUserId(((User) authentication.getPrincipal()).getUsername());
            sysLog.setAuthorities(Arrays.toString(this.getRoleFromAuthorities(authentication.getAuthorities()).toArray()));
            sysLog.setIp(((WebAuthenticationDetails)authentication.getDetails()).getRemoteAddress());
            sysLog.setResultMessage(ResponseStatus.SUCCESS.getMessage());
        }

        if (ex != null) {
            sysLog.setResultMessage(ResponseStatus.FAIL.getMessage());
            if (ex instanceof UsernameNotFoundException || ex instanceof BadCredentialsException) {
                sysLog.setData(ResponseDataMsg.UserNameORPasswordError.getMsg());
            } else {
                sysLog.setData(ResponseDataMsg.Fail.getMsg());
            }
        }

        sysLog.setCreateTime(DateUtilCustom.getTime());

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

    /**
     * 添加日志
     * @param sysLog    预添加的日志
     */
    public void addLog(SysLog sysLog) {
        sysLogMapper.insert(sysLog);
    }
}
