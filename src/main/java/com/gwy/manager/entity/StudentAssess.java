package com.gwy.manager.entity;

import java.util.Date;

public class StudentAssess {
    private String studentNo;

    private String tcId;

    private Integer appraiseScore;

    private Date submitTime;

    private String appraiseContent;

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo == null ? null : studentNo.trim();
    }

    public String getTcId() {
        return tcId;
    }

    public void setTcId(String tcId) {
        this.tcId = tcId == null ? null : tcId.trim();
    }

    public Integer getAppraiseScore() {
        return appraiseScore;
    }

    public void setAppraiseScore(Integer appraiseScore) {
        this.appraiseScore = appraiseScore;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getAppraiseContent() {
        return appraiseContent;
    }

    public void setAppraiseContent(String appraiseContent) {
        this.appraiseContent = appraiseContent == null ? null : appraiseContent.trim();
    }
}