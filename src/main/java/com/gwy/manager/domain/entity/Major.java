package com.gwy.manager.domain.entity;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
public class Major {
    private String majorId;

    private String majorName;

    private String deptId;

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId == null ? null : majorId.trim();
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName == null ? null : majorName.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    @Override
    public String toString() {
        return "Major{" +
                "majorId='" + majorId + '\'' +
                ", majorName='" + majorName + '\'' +
                ", deptId='" + deptId + '\'' +
                '}';
    }
}