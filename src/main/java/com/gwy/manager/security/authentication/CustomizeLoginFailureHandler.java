package com.gwy.manager.security.authentication;

import com.alibaba.fastjson.JSONObject;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.util.ResultVOUtil;
import com.gwy.manager.util.SysLogUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SysLogUtil logUtil;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException ex) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");

        ResultVO resultVO;

        if (ex instanceof UsernameNotFoundException || ex instanceof BadCredentialsException) {
            resultVO = ResultVOUtil.error(ResponseDataMsg.UserNameORPasswordError.getMsg());
        } else {
            resultVO = ResultVOUtil.error(ResponseDataMsg.Fail.getMsg());
        }

        //添加登录失败日志
        logUtil.addLoginLog(request, ex);

        response.getWriter().write(JSONObject.toJSONString(resultVO));
    }
}
