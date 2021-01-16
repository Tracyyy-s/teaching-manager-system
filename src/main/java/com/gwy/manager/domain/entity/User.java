package com.gwy.manager.domain.entity;

import java.util.Date;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
public class User {
    private String userId;

    private String username;

    private Integer gender;

    private String password;

    private String email;

    private Date birth;

    private String degree;

    private String graduated;

    private String political;

    private Integer entryYear;

    private Integer sumHour;

    private String deptId;

    private String professional;

    private Date professionalTime;

    private String availableDeptIds;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        this.password = password;
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
        this.degree = degree;
    }

    public String getGraduated() {
        return graduated;
    }

    public void setGraduated(String graduated) {
        this.graduated = graduated;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public Integer getEntryYear() {
        return entryYear;
    }

    public void setEntryYear(Integer entryYear) {
        this.entryYear = entryYear;
    }

    public Integer getSumHour() {
        return sumHour;
    }

    public void setSumHour(Integer sumHour) {
        this.sumHour = sumHour;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public Date getProfessionalTime() {
        return professionalTime;
    }

    public void setProfessionalTime(Date professionalTime) {
        this.professionalTime = professionalTime;
    }

    public String getAvailableDeptIds() {
        return availableDeptIds;
    }

    public void setAvailableDeptIds(String availableDeptIds) {
        this.availableDeptIds = availableDeptIds;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", degree='" + degree + '\'' +
                ", graduated='" + graduated + '\'' +
                ", political='" + political + '\'' +
                ", entryYear=" + entryYear +
                ", sumHour=" + sumHour +
                ", deptId='" + deptId + '\'' +
                ", professional='" + professional + '\'' +
                ", professionalTime=" + professionalTime +
                ", availableDeptIds='" + availableDeptIds + '\'' +
                '}';
    }
}