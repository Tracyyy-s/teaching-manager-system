package com.gwy.manager.security.filter;

import java.util.Collection;

/**
 * @author Tracy
 * @date 2020/12/14 22:09
 */
public class CaseUser {

    private String username;
    private String password;
    private Collection<String> authorities;

    public CaseUser() {
    }

    public CaseUser(String username, String password, Collection<String> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<String> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "CaseUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
