package com.gwy.manager.config.redis;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

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

    @Bean("studentTermTargets")
    public KeyGenerator studentTermTargetKey() {
        return (target, method, params) -> "student:" + params[0];
    }

    @Bean("teacherTermTargets")
    public KeyGenerator teacherTermTargetKey() {
        return (target, method, params) -> "teacher:" + params[0];
    }

    @Bean("currentTerm")
    public KeyGenerator currentTermKey() {
        return (target, method, params) -> "currentTerm";
    }
}
