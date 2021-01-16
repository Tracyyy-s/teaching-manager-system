package com.gwy.manager.domain.entity;

/**
 * @author Tracy
 * @date 2020/12/10 15:40
 */
public class SysPermissionApi {
    private Integer permissionId;

    private String api;

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api == null ? null : api.trim();
    }

    @Override
    public String toString() {
        return "SysPermissionApi{" +
                "permissionId=" + permissionId +
                ", api='" + api + '\'' +
                '}';
    }
}