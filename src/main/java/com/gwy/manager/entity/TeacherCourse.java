package com.gwy.manager.entity;

public class TeacherCourse {
    private String tcId;

    private Integer courseNo;

    private Integer teacherNo;

    private Short termId;

    private Integer appraiseSum;

    private String address;

    private Integer beginWeek;

    private Integer endWeek;

    public String getTcId() {
        return tcId;
    }

    public void setTcId(String tcId) {
        this.tcId = tcId == null ? null : tcId.trim();
    }

    public Integer getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(Integer courseNo) {
        this.courseNo = courseNo;
    }

    public Integer getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(Integer teacherNo) {
        this.teacherNo = teacherNo;
    }

    public Short getTermId() {
        return termId;
    }

    public void setTermId(Short termId) {
        this.termId = termId;
    }

    public Integer getAppraiseSum() {
        return appraiseSum;
    }

    public void setAppraiseSum(Integer appraiseSum) {
        this.appraiseSum = appraiseSum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getBeginWeek() {
        return beginWeek;
    }

    public void setBeginWeek(Integer beginWeek) {
        this.beginWeek = beginWeek;
    }

    public Integer getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(Integer endWeek) {
        this.endWeek = endWeek;
    }
}