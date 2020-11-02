package com.gwy.manager.entity;

import java.util.Date;

/**
 * @author TRacy
 */
public class Teacher {
    private String tno;

    private String name;

    private String password;

    private String birth;

    private String degree;

    private String graduated;

    private String political;

    private String entryYear;

    private String entryDept;

    private String professional;

    private Date professionalTime;

    private String deptId;

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno == null ? null : tno.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth == null ? null : birth.trim();
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

    public String getEntryYear() {
        return entryYear;
    }

    public void setEntryYear(String entryYear) {
        this.entryYear = entryYear == null ? null : entryYear.trim();
    }

    public String getEntryDept() {
        return entryDept;
    }

    public void setEntryDept(String entryDept) {
        this.entryDept = entryDept == null ? null : entryDept.trim();
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

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }
}