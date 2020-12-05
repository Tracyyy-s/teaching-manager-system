package com.gwy.manager.security.authentication;

import com.alibaba.fastjson.JSONObject;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.service.impl.SysLogServiceImpl;
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

/**
 * @author Tracy
 * @date 2020/11/22 12:54
 */
@Component
public class CustomizeLogoutHandler implements LogoutSuccessHandler {

    @Autowired
    private SysLogServiceImpl logService;

    private Logger logger = LoggerFactory.getLogger(CustomizeLogoutHandler.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");

        ResultVO resultVO;

        if (authentication == null) {
            logger.error("Authentication is null");
            resultVO = ResultVOUtil.error(ResponseDataMsg.NotLogin.getMsg());
        } else {
            //添加登出成功日志
            logService.addLog(request, authentication);

            resultVO = ResultVOUtil.success("Logout Success");
        }

        response.getWriter().write(JSONObject.toJSONString(resultVO));
    }
}

