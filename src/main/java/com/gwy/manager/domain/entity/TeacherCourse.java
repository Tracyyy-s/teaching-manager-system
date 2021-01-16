package com.gwy.manager.domain.entity;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
public class TeacherCourse {
    private String tcId;

    private String courseNo;

    private String teacherNo;

    private String termId;

    private Integer appraiseSum;

    private String address;

    private String time;

    private Integer beginWeek;

    private Integer endWeek;

    public String getTcId() {
        return tcId;
    }

    public void setTcId(String tcId) {
        this.tcId = tcId == null ? null : tcId.trim();
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo == null ? null : courseNo.trim();
    }

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo == null ? null : teacherNo.trim();
    }

    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId == null ? null : termId.trim();
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    @Override
    public String toString() {
        return "TeacherCourse{" +
                "tcId='" + tcId + '\'' +
                ", courseNo='" + courseNo + '\'' +
                ", teacherNo='" + teacherNo + '\'' +
                ", termId='" + termId + '\'' +
                ", appraiseSum=" + appraiseSum +
                ", address='" + address + '\'' +
                ", time='" + time + '\'' +
                ", beginWeek=" + beginWeek +
                ", endWeek=" + endWeek +
                '}';
    }
}