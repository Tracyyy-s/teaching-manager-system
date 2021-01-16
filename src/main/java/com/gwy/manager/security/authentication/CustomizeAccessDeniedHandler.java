package com.gwy.manager.security.authentication;

import com.alibaba.fastjson.JSONObject;
import com.gwy.manager.domain.enums.ResponseDataMsg;
import com.gwy.manager.security.exception.GlobalAuthorizeException;
import com.gwy.manager.service.impl.SysLogServiceImpl;
import com.gwy.manager.util.ResultVoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Tracy
 * @date 2020/11/23 19:03
 */
@Component
public class CustomizeAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    SysLogServiceImpl logService;

    /**
     * 自定义处理权限拒绝异常
     * @param request   请求
     * @param response  返回
     * @param accessDeniedException 异常实体
     * @throws IOException  异常
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {

        response.setContentType("application/json;charset=UTF-8");

        logService.addLog(request, new GlobalAuthorizeException(ResponseDataMsg.PermissionDeny.name()));

        response.getWriter().write(JSONObject.toJSONString(ResultVoUtil.error(ResponseDataMsg.PermissionDeny.getMsg())));
    }
}
