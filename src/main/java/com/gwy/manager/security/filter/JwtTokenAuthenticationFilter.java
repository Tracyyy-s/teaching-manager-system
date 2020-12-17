package com.gwy.manager.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.cxytiandi.encrypt.util.AesEncryptUtils;
import com.gwy.manager.constant.EncodeConstant;
import com.gwy.manager.constant.PassRequestPaths;
import com.gwy.manager.redis.RedisUtil;
import com.gwy.manager.request.WebHttpServletRequestWrapper;
import com.gwy.manager.security.UserDetailServiceImpl;
import com.gwy.manager.util.JwtTokenUtils;
import com.gwy.manager.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Tracy
 * @date 2020/12/1 15:44
 */
@Component
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private static final String FILE = "file";

    private static final String POST = "POST";

    private static final List<String> PASS_REQUESTS = new ArrayList<>();

    static {
        PASS_REQUESTS.add(PassRequestPaths.ROOT_REQUEST);
        PASS_REQUESTS.add(PassRequestPaths.LOGIN_REQUEST);
        PASS_REQUESTS.add(PassRequestPaths.SEND_CODE_REQUEST);
        PASS_REQUESTS.add(PassRequestPaths.UPDATE_PASSWORD_REQUEST);
    }

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        //获取header中的验证信息
        String authHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);

        //不含有token，且不包含放行的请求，返回错误
        if (authHeader == null && !PASS_REQUESTS.contains(request.getServletPath()) && !request.getMethod().equals(POST)) {
            response.getWriter().write(JSONObject.toJSONString(ResultVOUtil.error("No Token")));
            return;
        }

        ServletRequest requestWrapper = null;

        if (authHeader != null) {

            //如果解密后的token格式不正确
            if (!authHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
                response.getWriter().write(JSONObject.toJSONString(ResultVOUtil.error("Error Token Format")));
                return;
            }

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

            //根据token校验用户
            doFilterInSecurity(username, authToken, request);

            //如果没有file类参数，则创建新的request
            if (request.getParameter(FILE) == null) {
                requestWrapper = new WebHttpServletRequestWrapper(request, username);
            }
        }

        if (requestWrapper != null) {
            chain.doFilter(requestWrapper, response);
            return;
        }

        chain.doFilter(request, response);
    }

    /**
     * 根据token和username自定义过滤器
     * @param username  用户id
     * @param token token
     * @param request   请求体
     */
    private void doFilterInSecurity(String username, String token, HttpServletRequest request) {

        //从redis中根据token获取用户
        Object objUser = redisUtil.get(token);

        CaseUser user;
        //若用户存在
        if (objUser != null) {
            user = ((CaseUser) objUser);
        } else {
            //用户不存在则查询
            UserDetails userDetails = this.userDetailService.loadUserByUsername(username);

            //获得所有的角色名
            List<String> roleList = new ArrayList<>();
            for (GrantedAuthority authority : userDetails.getAuthorities()) {
                roleList.add(authority.getAuthority());
            }

            //创建自定义的样例类用于redis的序列化
            user = new CaseUser(userDetails.getUsername(), userDetails.getPassword(), roleList);

            //放入redis中
            redisUtil.set(token, user);

            //设置token生存8小时
            redisUtil.expire(token, 60 * 60 * 8);
        }

        //获得user的权限列表并转换
        Collection<String> strAuthorities = user.getAuthorities();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String strAuthority : strAuthorities) {
            authorities.add(new SimpleGrantedAuthority(strAuthority));
        }

        //创建环境user
        User userInContext = new User(user.getUsername(), user.getPassword(), authorities);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userInContext, null, userInContext.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                request));

        //设置Context中认证信息为本用户信息
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }
}
