package com.gwy.manager.domain.entity;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
public class UserRole {
    private String userId;

    private Integer roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}