package com.gwy.manager.config.redis;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Tracy
 * @date 2020/11/9 13:36
 */
@Configuration
public class KeyGeneratorConfiguration {

    @Bean("allTerms")
    public KeyGenerator termsKey() {
        return (target, method, params) -> "all";
    }
}
