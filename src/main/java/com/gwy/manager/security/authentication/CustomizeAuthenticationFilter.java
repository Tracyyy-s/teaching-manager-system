package com.gwy.manager.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/21 23:26
 */
public class CustomizeAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

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
