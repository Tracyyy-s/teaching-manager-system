package com.gwy.manager.security.authentication;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.enums.ResponseDataMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Tracy
 * @date 2020/11/23 19:03
 */
@Component
public class CustomizeAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setContentType("application/json;charset=UTF-8");

        ResultVO resultVO = new ResultVO();

        resultVO.setData(ResponseDataMsg.PermissionDeny.getMsg());

        response.getWriter().write(JSONObject.toJSONString(resultVO));
    }
}
