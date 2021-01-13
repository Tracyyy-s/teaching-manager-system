package com.gwy.manager.security.authentication;

import com.alibaba.fastjson.JSONObject;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.service.impl.SysLogServiceImpl;
import com.gwy.manager.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Tracy
 * @date 2020/11/21 23:38
 */
@Component
public class CustomizeLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private SysLogServiceImpl logService;

    /**
     * 自定义登录成功处理器
     * @param request   请求体
     * @param response  响应体
     * @param authentication    认证信息
     * @throws IOException  抛出异常
     * @throws ServletException 异常体
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        response.setContentType("application/json;charset=UTF-8");

        //添加登录成功日志
        logService.addLog(request, authentication);

        response.getWriter().write(JSONObject.toJSONString(ResultVOUtil.success(ResponseDataMsg.Success.getMsg())));
    }

}
