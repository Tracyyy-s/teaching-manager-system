package com.gwy.manager.config.redis;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.List;

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

    @Bean("userRoleIds")
    public KeyGenerator userRoleIds() {
        return (target, method, params) -> "'" + params[0] + "'" + "roleIds";
    }

    @Bean("userRoles")
    public KeyGenerator userRoles() {
         return (target, method, params) -> {
             if (params[0] != null) {
                 return "'" + params[0] + "'" + "roles";
             }
             return "NULL";
         };
    }

    @SuppressWarnings("unchecked")
    @Bean("byRoleIds")
    public KeyGenerator permissionsByRoleIds() {
        return (target, method, params) -> {

            List<Integer> roleIds = (List<Integer>) params[0];
            return "roleIds::" + roleIds.toString();
        };
    }

    @Bean("byRoleId")
    public KeyGenerator permissionsByRoleId() {
        return (target, method, params) -> "roleIds::" + params[0];
    }

    @SuppressWarnings("unchecked")
    @Bean("byIds")
    public KeyGenerator permissionByIds() {
        return (target, method, params) -> {
            List<Integer> ids = (List<Integer>) params[0];
            return "ids::" + ids.toString();
        };
    }
}
