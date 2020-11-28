package com.gwy.manager.security.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;

/**
 * @author Tracy
 * @date 2020/11/23 13:27
 */
public class AuthenticationUtil {

    protected static String getRoleName(Authentication authentication) {
        Object[] roleNames = authentication.getAuthorities().toArray();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < roleNames.length; i++) {
            sb.append(roleNames[i]);
            if (i != roleNames.length - 1) {
                sb.append("|");
            }
        }
        return sb.toString();
    }

    protected static String getUsername(Authentication authentication) {
        return ((User) authentication.getPrincipal()).getUsername();
    }

    protected static String getRoleNameNotWithRole(Authentication authentication) {
        String[] roleNames = getRoleName(authentication).split("\\|");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < roleNames.length; i++) {
            sb.append(roleNames[i].split("_")[1]);
            if (i != roleNames.length - 1) {
                sb.append("|");
            }
        }

        return sb.toString();
    }
}
