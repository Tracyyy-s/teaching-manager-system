package com.gwy.manager.security.authentication;

import com.alibaba.fastjson.JSONObject;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.redis.RedisUtil;
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

/**
 * @author Tracy
 * @date 2020/11/22 12:54
 */
@Component
public class CustomizeLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private RedisUtil redisUtil;

    private Logger logger = LoggerFactory.getLogger(CustomizeLogoutSuccessHandler.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");

        ResultVO resultVO = new ResultVO();

        if (authentication == null) {
            logger.error("Authentication is null");
            resultVO.setData(ResponseDataMsg.NotLogin.getMsg());
        } else {
            String roleName = AuthenticationUtil.getRoleNameNotWithRole(authentication);
            String username = AuthenticationUtil.getUsername(authentication);

            //获得redis中User的Key
            String keyInRedis = redisUtil.getUserKeyInRedis(roleName, username);

            //删除redis中相关user的key
            redisUtil.del(keyInRedis);

            resultVO.success("Logout Success");
        }

        response.getWriter().write(JSONObject.toJSONString(resultVO));
    }
}

