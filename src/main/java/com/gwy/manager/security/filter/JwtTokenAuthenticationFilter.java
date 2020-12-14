package com.gwy.manager.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.gwy.manager.entity.User;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.request.WebHttpServletRequestWrapper;
import com.gwy.manager.security.UserDetailServiceImpl;
import com.gwy.manager.util.JwtTokenUtils;
import com.gwy.manager.util.ResultVOUtil;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tracy
 * @date 2020/12/1 15:44
 */
@Component
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private static final String FILE = "file";

    private static final String LOGIN = "/login";

    private static final String SEND_CODE = "/sendCode";

    private static final String UPDATE_PASSWORD = "/updatePassword";

    private static final String POST = "POST";

    private static final List<String> PASS_REQUESTS = new ArrayList<>();

    static {
        PASS_REQUESTS.add(LOGIN);
        PASS_REQUESTS.add(SEND_CODE);
        PASS_REQUESTS.add(UPDATE_PASSWORD);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        //获取header中的验证信息
        String authHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);

        ServletRequest requestWrapper = null;

        //如果含有token就执行
        if (authHeader != null && authHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
            final String authToken = authHeader.substring(JwtTokenUtils.TOKEN_PREFIX.length());

            //从token中获取用户信息，jwtUtils自定义的token加解密方式
            String username = JwtTokenUtils.getUsername(authToken);

            //如果token不正确
            if (username.equals(JwtTokenUtils.ERROR_TOKEN)) {
                response.getWriter().write(JSONObject.toJSONString(ResultVOUtil.error("Error Token")));
                return;
            }

            //如果token已经过期
            if (JwtTokenUtils.isExpiration(authToken)) {
                response.getWriter().write(JSONObject.toJSONString(ResultVOUtil.error("Token Is Expiration")));
                return;
            }

            //如果没有file类参数，则创建新的request
            if (request.getParameter(FILE) == null) {
                requestWrapper = new WebHttpServletRequestWrapper(request, username);
            }

        } else if (!PASS_REQUESTS.contains(request.getRequestURI()) || !request.getMethod().equals(POST)) {
            //没有token
            response.getWriter().write(JSONObject.toJSONString(ResultVOUtil.error("No Token")));
            return;
        }

        if (requestWrapper != null) {
            chain.doFilter(requestWrapper, response);
            return;
        }

        chain.doFilter(request, response);
    }
}
