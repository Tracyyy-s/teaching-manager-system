package com.gwy.manager.config;

import com.gwy.manager.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tracy
 * @date 2020/11/3 16:47
 */
@Configuration
public class AppWebMvcConfig {

    /**
     * 添加拦截器组件
     * @return
     */
    //@Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }
}
