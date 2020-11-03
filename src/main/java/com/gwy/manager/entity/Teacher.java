package com.gwy.manager.entity;

import java.util.Date;

public class Teacher {
    private String teacherNo;

    private String teacherName;

    private Integer gender;

    private String password;

    private Date birth;

    private String degree;

    private String graduated;

    private String political;

    private Integer entryYear;

    private String deptId;

    private String professional;

    private Date professionalTime;

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo == null ? null : teacherNo.trim();
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree == null ? null : degree.trim();
    }

    public String getGraduated() {
        return graduated;
    }

    public void setGraduated(String graduated) {
        this.graduated = graduated == null ? null : graduated.trim();
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political == null ? null : political.trim();
    }

    public Integer getEntryYear() {
        return entryYear;
    }

    public void setEntryYear(Integer entryYear) {
        this.entryYear = entryYear;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional == null ? null : professional.trim();
    }

    public Date getProfessionalTime() {
        return professionalTime;
    }

    public void setProfessionalTime(Date professionalTime) {
        this.professionalTime = professionalTime;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherNo='" + teacherNo + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", gender=" + gender +
                ", password='" + password + '\'' +
                ", birth=" + birth +
                ", degree='" + degree + '\'' +
                ", graduated='" + graduated + '\'' +
                ", political='" + political + '\'' +
                ", entryYear=" + entryYear +
                ", deptId='" + deptId + '\'' +
                ", professional='" + professional + '\'' +
                ", professionalTime=" + professionalTime +
                '}';
    }
}