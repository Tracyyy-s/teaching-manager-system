package com.gwy.manager.security.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

/**
 * @author Tracy
 * @date 2020/11/23 13:27
 */
public class AuthenticationUtil {

    protected static String getRoleName(Authentication authentication) {
        return authentication.getAuthorities().toArray()[0].toString();
    }

    protected static String getUsername(Authentication authentication) {
        return ((User) authentication.getPrincipal()).getUsername();
    }

    protected static String getRoleNameNotWithRole(Authentication authentication) {
        return getRoleName(authentication).split("_")[1];
    }
}
