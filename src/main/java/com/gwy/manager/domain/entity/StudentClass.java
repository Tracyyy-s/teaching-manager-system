package com.gwy.manager.domain.entity;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
public class StudentClass {
    private String classId;

    private String majorId;

    private Integer stuCount;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId == null ? null : majorId.trim();
    }

    public Integer getStuCount() {
        return stuCount;
    }

    public void setStuCount(Integer stuCount) {
        this.stuCount = stuCount;
    }

    @Override
    public String toString() {
        return "StudentClass{" +
                "classId='" + classId + '\'' +
                ", majorId='" + majorId + '\'' +
                ", stuCount=" + stuCount +
                '}';
    }
}