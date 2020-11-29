package com.gwy.manager.util;

import com.alibaba.fastjson.JSONObject;
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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Locale;

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
        sysLog.setAuthorities(Arrays.toString(authentication.getAuthorities().toArray()));
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

    public void addLoginLog(HttpServletRequest request, Authentication authentication) {
        this.addLoginLog(request, authentication, null);
    }

    public void addLoginLog(HttpServletRequest request, AuthenticationException ex) {
        this.addLoginLog(request, null, ex);
    }

    private void addLoginLog(HttpServletRequest request, Authentication authentication, AuthenticationException ex) {
        SysLog sysLog = new SysLog();

        sysLog.setRequestUrl(request.getRequestURL().toString());
        sysLog.setLocale(request.getLocale().toString());
        if (authentication != null) {
            sysLog.setUserId(((User) authentication.getPrincipal()).getUsername());
            sysLog.setAuthorities(Arrays.toString(authentication.getAuthorities().toArray()));
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
     * 添加日志
     * @param sysLog    预添加
     */
    public void addLog(SysLog sysLog) {
        sysLogMapper.insert(sysLog);
    }
}
