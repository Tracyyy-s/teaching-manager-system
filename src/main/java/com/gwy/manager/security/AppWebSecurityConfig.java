package com.gwy.manager.security;

import com.gwy.manager.constant.RoleName;
import com.gwy.manager.security.authentication.*;
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
    CustomizeLogoutSuccessHandler customizeLogoutSuccessHandler;

    @Autowired
    CustomizeAuthenticationEntryPoint customizeAuthenticationEntryPoint;

    @Autowired
    CustomizeAccessDeniedHandler customizeAccessDeniedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //自定义用户验证和加密方式
        auth.userDetailsService(userDetailService).passwordEncoder(new GlobalPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()//  定义当需要用户登录时候，转到的登录页面。
                .and()
                .authorizeRequests().antMatchers("/**").permitAll()
//                // 定义哪些URL需要被保护、哪些不需要被保护
//                .authorizeRequests().antMatchers("/login").permitAll() //不需要保护的URL
//                .and()
//                //teacher访问路径
//                .authorizeRequests().antMatchers("/teacher/**").hasAnyRole(RoleName.TEACHER + "," + RoleName.LEADER)
//                .and()
//                //student访问路径
//                .authorizeRequests().antMatchers("/student/**").hasRole(RoleName.STUDENT)
//                .and()
//                //admin访问路径
//                .authorizeRequests().antMatchers("/admin/**").hasAnyRole(RoleName.ADMIN1 + "," + RoleName.ADMIN2)
//                .and()
//                //高级admin访问路径
//                .authorizeRequests().antMatchers("/adminplus/**").hasRole(RoleName.ADMIN2)
//                .and()
//                //root访问路径
//                .authorizeRequests().antMatchers("/root/**").hasRole(RoleName.ROOT)
                .and()
                .logout().permitAll() // 登出
                .logoutSuccessHandler(customizeLogoutSuccessHandler)
                .deleteCookies("JSESSIONID")
                .and()
                .csrf().disable();

        //配置自定义过滤器 增加post json 支持
        http.addFilterAt(UserAuthenticationFilterBean(), UsernamePasswordAuthenticationFilter.class);
        //配置访问错误
        http.exceptionHandling().authenticationEntryPoint(customizeAuthenticationEntryPoint);
        http.exceptionHandling().accessDeniedHandler(customizeAccessDeniedHandler); // 无权访问
    }

    private CustomizeAuthenticationFilter UserAuthenticationFilterBean() throws Exception {
        CustomizeAuthenticationFilter customizeAuthenticationFilter = new CustomizeAuthenticationFilter();
        customizeAuthenticationFilter.setAuthenticationManager(authenticationManager());
        customizeAuthenticationFilter.setAuthenticationSuccessHandler(customizeLoginSuccessHandler);
        customizeAuthenticationFilter.setAuthenticationFailureHandler(customizeLoginFailureHandler);
        return customizeAuthenticationFilter;
    }

}
