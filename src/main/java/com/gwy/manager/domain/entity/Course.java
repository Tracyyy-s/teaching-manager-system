package com.gwy.manager.domain.entity;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
public class Course {
    private String courseNo;

    private String courseName;

    private Integer hour;

    private Integer credit;

    private String deptId;

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo == null ? null : courseNo.trim();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseNo='" + courseNo + '\'' +
                ", courseName='" + courseName + '\'' +
                ", hour=" + hour +
                ", credit=" + credit +
                ", deptId='" + deptId + '\'' +
                '}';
    }
}