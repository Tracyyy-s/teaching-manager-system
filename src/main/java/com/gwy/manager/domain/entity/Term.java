package com.gwy.manager.domain.entity;

import java.util.Date;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
public class Term {
    private String termId;

    private String termName;

    private Date beginDate;

    private Date endDate;

    private Double studentWeight;

    private Double teacherWeight;

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId == null ? null : termId.trim();
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName == null ? null : termName.trim();
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStudentWeight(Double studentWeight) {
        this.studentWeight = studentWeight;
    }

    public void setTeacherWeight(Double teacherWeight) {
        this.teacherWeight = teacherWeight;
    }

    public Double getStudentWeight() {
        return studentWeight;
    }

    public Double getTeacherWeight() {
        return teacherWeight;
    }

    @Override
    public String toString() {
        return "Term{" +
                "termId='" + termId + '\'' +
                ", termName='" + termName + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", studentWeight=" + studentWeight +
                ", teacherWeight=" + teacherWeight +
                '}';
    }
}