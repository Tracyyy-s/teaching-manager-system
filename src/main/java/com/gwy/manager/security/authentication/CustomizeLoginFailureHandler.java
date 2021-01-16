package com.gwy.manager.security.authentication;

import com.alibaba.fastjson.JSONObject;
import com.gwy.manager.domain.enums.ResponseDataMsg;
import com.gwy.manager.security.exception.GlobalAuthorizeException;
import com.gwy.manager.service.impl.SysLogServiceImpl;
import com.gwy.manager.util.ResultVoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Tracy
 * @date 2020/11/22 0:04
 */
@Component
public class CustomizeLoginFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private SysLogServiceImpl logService;

    /**
     * 自定义登录异常
     * @param request   请求体
     * @param response  响应体
     * @param ex    抛出异常
     * @throws IOException  throwable
     * @throws ServletException throwable
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException ex) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");

        //添加登录失败日志
        logService.addLog(request, new GlobalAuthorizeException(ResponseDataMsg.UserNameORPasswordError.name()));

        response.getWriter().write(JSONObject.toJSONString(ResultVoUtil.error(ResponseDataMsg.UserNameORPasswordError.getMsg())));
    }
}
