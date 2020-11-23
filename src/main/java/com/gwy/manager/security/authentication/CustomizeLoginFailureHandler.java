package com.gwy.manager.security.authentication;

import com.alibaba.fastjson.JSONObject;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.enums.ResponseDataMsg;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException ex) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");

        ResultVO resultVO = new ResultVO();

        if (ex instanceof UsernameNotFoundException || ex instanceof BadCredentialsException) {
            resultVO.setData(ResponseDataMsg.UserNameORPasswordError.getMsg());
        } else {
            resultVO.setData(ResponseDataMsg.Fail.getMsg());
        }

        response.getWriter().write(JSONObject.toJSONString(resultVO));
    }
}
