package com.gwy.manager.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gwy.manager.util.JwtTokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author Tracy
 * @date 2020/11/21 23:26
 */
public class JwtLoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private ThreadLocal<Map<String, String>> threadLocal = new ThreadLocal<>();

    private static final String LOGIN_ACCOUNT_KEY = "account";
    private static final String LOGIN_PASSWORD_KEY = "password";

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        String username = this.getBodyParams(request).get(LOGIN_ACCOUNT_KEY);
        if (!StringUtils.isEmpty(username)) {
            return username;
        }
        return super.obtainUsername(request);
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        String password = this.getBodyParams(request).get(LOGIN_PASSWORD_KEY);
        if (!StringUtils.isEmpty(password)) {
            return password;
        }
        return super.obtainPassword(request);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                obtainUsername(request), obtainPassword(request), new ArrayList<>());

        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        super.successfulAuthentication(request, response, chain, authResult);

        String username = ((User) authResult.getPrincipal()).getUsername();
        String token = JwtTokenUtils.createToken(username, false);

        // 返回创建成功的token
        // 但是这里创建的token只是单纯的token
        // 按照jwt的规定，最后请求的格式应该是 `Bearer token`
        response.setHeader(JwtTokenUtils.TOKEN_HEADER, JwtTokenUtils.TOKEN_PREFIX + token);
    }

    /**
     * 自定义请求参数类型
     * @param request   请求
     * @return  返回Map
     */
    @SuppressWarnings("unchecked")
    private Map<String, String> getBodyParams(HttpServletRequest request) {

        Map<String, String> bodyParams = threadLocal.get();

        if (bodyParams == null) {
            ObjectMapper objectMapper = new ObjectMapper();

            try (InputStream is = request.getInputStream()) {
                bodyParams = objectMapper.readValue(is, Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (bodyParams == null) {
                bodyParams = new HashMap<>();
            }

            threadLocal.set(bodyParams);
        }

        return bodyParams;
    }
}