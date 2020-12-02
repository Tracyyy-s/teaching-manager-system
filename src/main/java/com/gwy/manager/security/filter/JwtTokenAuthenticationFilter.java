package com.gwy.manager.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.gwy.manager.security.UserDetailServiceImpl;
import com.gwy.manager.util.JwtTokenUtils;
import com.gwy.manager.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Tracy
 * @date 2020/12/1 15:44
 */
@Component
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailServiceImpl userDetailService; //用户信息service

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        //获取header中的验证信息
        String authHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);

        //如果含有token就执行，否则就放行
        if (authHeader != null && authHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
            final String authToken = authHeader.substring(JwtTokenUtils.TOKEN_PREFIX.length());

            //从token中获取用户信息，jwtUtils自定义的token加解密方式
            String username = JwtTokenUtils.getUsername(authToken);

            //如果用户名存在但环境中不存在则添加
            if (!username.equals(JwtTokenUtils.ERROR_TOKEN) && SecurityContextHolder.getContext().getAuthentication() == null) {
                //根据用户名获取用户对象
                UserDetails userDetails = userDetailService.loadUserByUsername(username);

                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    //设置为已登录
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } else if (username.equals(JwtTokenUtils.ERROR_TOKEN)) {
                //如果token不正确
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write(JSONObject.toJSONString(ResultVOUtil.error("Error Token")));
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
