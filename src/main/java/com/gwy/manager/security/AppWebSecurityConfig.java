package com.gwy.manager.security;

import com.gwy.manager.security.authentication.*;
import com.gwy.manager.security.filter.JwtTokenAuthenticationFilter;
import com.gwy.manager.security.filter.JwtLoginAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @author Tracy
 * @date 2020/11/21 23:19
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailServiceImpl userDetailService;

    @Autowired
    CustomizeLoginSuccessHandler customizeLoginSuccessHandler;

    @Autowired
    CustomizeLoginFailureHandler customizeLoginFailureHandler;

    @Autowired
    CustomizeLogoutHandler customizeLogoutHandler;

    @Autowired
    CustomizeAuthenticationEntryPoint customizeAuthenticationEntryPoint;

    @Autowired
    CustomizeAccessDeniedHandler customizeAccessDeniedHandler;

    @Autowired
    JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //自定义用户验证和加密方式
        auth.userDetailsService(userDetailService).passwordEncoder(new GlobalPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //配置自定义过滤器 增加post json 支持
        http.addFilterAt(jwtLoginFilterBean(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtTokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http
                .formLogin()//  定义当需要用户登录时候，转到的登录页面。
                .and()
//                .authorizeRequests().antMatchers("/**").permitAll()
//                // 定义哪些URL需要被保护、哪些不需要被保护
                .authorizeRequests().antMatchers("/login").permitAll() //不需要保护的URL
                .anyRequest().authenticated()
                .and()
                .logout().permitAll() // 登出
                .logoutSuccessHandler(customizeLogoutHandler)
                .invalidateHttpSession(true)
                .and()
                //不需要session
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //.and()
                .csrf().disable();
        //访问错误配置
        //未登录
        http
                .exceptionHandling()
                .authenticationEntryPoint(customizeAuthenticationEntryPoint)
                .accessDeniedHandler(customizeAccessDeniedHandler);
    }

    private JwtLoginAuthenticationFilter jwtLoginFilterBean() throws Exception {
        JwtLoginAuthenticationFilter jwtLoginAuthenticationFilter = new JwtLoginAuthenticationFilter();
        jwtLoginAuthenticationFilter.setAuthenticationManager(authenticationManager());
        jwtLoginAuthenticationFilter.setAuthenticationSuccessHandler(customizeLoginSuccessHandler);
        jwtLoginAuthenticationFilter.setAuthenticationFailureHandler(customizeLoginFailureHandler);
        return jwtLoginAuthenticationFilter;
    }

}
