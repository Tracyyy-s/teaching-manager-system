package com.gwy.manager.security.authentication;

import com.alibaba.fastjson.JSONObject;
import com.gwy.manager.domain.enums.ResponseDataMsg;
import com.gwy.manager.security.exception.GlobalAuthorizeException;
import com.gwy.manager.service.impl.SysLogServiceImpl;
import com.gwy.manager.util.ResultVoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Tracy
 * @date 2020/11/23 13:46
 */
@Component
public class CustomizeAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    SysLogServiceImpl logService;

    /**
     * 自定义未登录访问异常
     * @param request   请求体
     * @param response  响应体
     * @param authException 权限异常
     * @throws IOException  throwable
     * @throws ServletException throwable
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");

        logService.addLog(request, new GlobalAuthorizeException(ResponseDataMsg.NotLogin.name()));

        response.getWriter().write(JSONObject.toJSONString(ResultVoUtil.error(ResponseDataMsg.NotLogin.getMsg())));
    }
}
