package com.gwy.manager.request;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * @author Tracy
 * @date 2020/12/13 19:39
 */
public class HttpRequestUtil {

    private static final String ADMIN_NO = "adminNo";
    private static final String STUDENT_NO = "studentNo";
    private static final String USER_ID = "userId";
    private static final String ACCOUNT = "account";

    /**
     * 根据token解析出来的username, 将其重新添加到request中
     * @param request   请求
     * @param username  用户名
     * @return  结果集
     */
    @SuppressWarnings("unchecked")
    public static String getBodyContent(HttpServletRequest request, String username) {
        HashMap<String, Object> bodyParams = new HashMap<>(10);

        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = request.getInputStream()) {
            bodyParams = objectMapper.readValue(inputStream, HashMap.class);
        } catch (IOException ignored) {

        }

        bodyParams.putIfAbsent(ADMIN_NO, username);
        bodyParams.putIfAbsent(USER_ID, username);
        bodyParams.putIfAbsent(STUDENT_NO, username);
        bodyParams.putIfAbsent(ACCOUNT, username);

        return JSONObject.toJSONString(bodyParams);
    }
}
