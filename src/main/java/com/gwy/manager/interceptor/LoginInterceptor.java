package com.gwy.manager.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Tracy
 * @date 2020/11/3 16:46
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        Object student = session.getAttribute("student");
        Object teacher = session.getAttribute("teacher");
        Object admin = session.getAttribute("admin");

        if (student == null && teacher == null && admin == null) {

        }
        return false;
    }
}
