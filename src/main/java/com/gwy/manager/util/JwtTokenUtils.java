package com.gwy.manager.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author Tracy
 * @date 2020/11/30 23:24
 */
public class JwtTokenUtils {

    /**
     * Token认证头
     */
    public static final String TOKEN_HEADER    =      "Authorization";

    /**
     * Token前缀
     */
    public static final String TOKEN_PREFIX    =      "Bearer ";

    /**
     * 错误Token返回
     */
    public static final String ERROR_TOKEN     =      "ERROR_TOKEN_2flk9fnca";

    /**
     * Token密钥
     */
    private static final String SECRET         =      "jwtsecretdemo";

    /**
     * ISS
     */
    private static final String ISS            =      "echisan";


    /**
     * 过期时间是3600 * 8秒，即是8个小时
     */
    private static final long EXPIRATION = 8 * 3600L;

    /**
     * 选择了记住我之后的过期时间为7天
     */
    private static final long EXPIRATION_REMEMBER = 604800L;

    /**
     * 创建token
     * @param username  用户名
     * @param isRememberMe  记住我
     * @return  结果集
     */
    public static String createToken(String username, boolean isRememberMe) {
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setIssuer(ISS)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }

    /**
     * 从token中获取用户名
     * @param token token
     * @return  结果
     */
    public static String getUsername(String token) {
        try {
            return getTokenBody(token).getSubject();
        } catch (Exception e) {
            return ERROR_TOKEN;
        }
    }

    /**
     * 是否已过期
     * @param token token
     * @return  结果
     */
    public static boolean isExpiration(String token) {
        return getTokenBody(token).getExpiration().before(new Date());
    }

    /**
     * 获得token体
     * @param token token
     * @return  结果集
     */
    public static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
