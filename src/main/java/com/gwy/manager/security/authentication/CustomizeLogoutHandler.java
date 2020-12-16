package com.gwy.manager.security.authentication;

import com.alibaba.fastjson.JSONObject;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.redis.RedisUtil;
import com.gwy.manager.service.impl.SysLogServiceImpl;
import com.gwy.manager.util.JwtTokenUtils;
import com.gwy.manager.util.ResultVOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**
 * @author Tracy
 * @date 2020/11/22 12:54
 */
@Component
public class CustomizeLogoutHandler implements LogoutSuccessHandler {

    @Autowired
    private SysLogServiceImpl logService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");

        //添加登出成功日志
        logService.addLog(request, authentication);

        //获得request的认证信息
        String authHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        if (authHeader != null) {
            //获得当前请求的token
            String token = authHeader.substring(JwtTokenUtils.TOKEN_PREFIX.length());

            //redis中删除相应的token
            redisUtil.del(token);
        }


        response.getWriter().write(JSONObject.toJSONString(ResultVOUtil.success("Logout Success")));
    }
}

