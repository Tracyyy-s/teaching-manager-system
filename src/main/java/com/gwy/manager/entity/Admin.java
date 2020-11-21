package com.gwy.manager.entity;

public class Admin {
    private String adminNo;

    private String adminName;

    private String password;

    private String deptId;

    private String availableDeptIds;

    public String getAdminNo() {
        return adminNo;
    }

    public void setAdminNo(String adminNo) {
        this.adminNo = adminNo == null ? null : adminNo.trim();
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getAvailableDeptIds() {
        return availableDeptIds;
    }

    public void setAvailableDeptIds(String availableDeptIds) {
        this.availableDeptIds = availableDeptIds;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminNo='" + adminNo + '\'' +
                ", adminName='" + adminName + '\'' +
                ", password='" + password + '\'' +
                ", deptId='" + deptId + '\'' +
                ", availableDeptIds='" + availableDeptIds + '\'' +
                '}';
    }
}