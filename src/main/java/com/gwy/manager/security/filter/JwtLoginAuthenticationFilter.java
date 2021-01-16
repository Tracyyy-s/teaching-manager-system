package com.gwy.manager.security.filter;

import com.cxytiandi.encrypt.util.AesEncryptUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gwy.manager.domain.constant.EncodeConstant;
import com.gwy.manager.util.JwtTokenUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/21 23:26
 */
public class JwtLoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private ThreadLocal<Map<String, String>> threadLocal = new ThreadLocal<>();

    private static final String LOGIN_ACCOUNT_KEY = "account";
    private static final String LOGIN_PASSWORD_KEY = "password";

    /**
     * 重写解析username方法
     * @param request   请求体
     * @return  结果集
     */
    @Override
    protected String obtainUsername(HttpServletRequest request) {
        String encodedUsername = this.getBodyParams(request).get(LOGIN_ACCOUNT_KEY);
        try {
            return AesEncryptUtils.aesDecrypt(encodedUsername, EncodeConstant.SALT);
        } catch (Exception e) {
            return super.obtainUsername(request);
        }
    }

    /**
     * 重写解析password的方法
     * @param request   请求体
     * @return  结果集
     */
    @Override
    protected String obtainPassword(HttpServletRequest request) {
        String encodedPassword = this.getBodyParams(request).get(LOGIN_PASSWORD_KEY);
        try {
            return AesEncryptUtils.aesDecrypt(encodedPassword, EncodeConstant.SALT);
        } catch (Exception e) {
            return super.obtainUsername(request);
        }
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
        String authToken = JwtTokenUtils.TOKEN_PREFIX + token;

        /*

        //将token进行加密
        String encodedToken;
        try {
            encodedToken = AesEncryptUtils.aesEncrypt(authToken, EncodeConstant.SALT);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write(JSONObject.toJSONString(ResultVoUtil.error("Encode Token Error")));
            return;
        }

         */

        response.setHeader(JwtTokenUtils.TOKEN_HEADER, authToken);
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
                bodyParams = new HashMap<>(4);
            }

            threadLocal.set(bodyParams);
        }

        return bodyParams;
    }
}
