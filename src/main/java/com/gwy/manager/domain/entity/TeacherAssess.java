package com.gwy.manager.domain.entity;

import java.util.Date;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
public class TeacherAssess {
    private String teacherNo;

    private String assessedTeacherNo;

    private String termId;

    private Integer appraiseScore;

    private Date submitTime;

    private String appraiseContent;

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo == null ? null : teacherNo.trim();
    }

    public String getAssessedTeacherNo() {
        return assessedTeacherNo;
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    public void setAssessedTeacherNo(String assessedTeacherNo) {
        this.assessedTeacherNo = assessedTeacherNo == null ? null : assessedTeacherNo.trim();
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

    @Override
    public String toString() {
        return "TeacherAssess{" +
                "teacherNo='" + teacherNo + '\'' +
                ", assessedTeacherNo='" + assessedTeacherNo + '\'' +
                ", termId='" + termId + '\'' +
                ", appraiseScore=" + appraiseScore +
                ", submitTime=" + submitTime +
                ", appraiseContent='" + appraiseContent + '\'' +
                '}';
    }
}