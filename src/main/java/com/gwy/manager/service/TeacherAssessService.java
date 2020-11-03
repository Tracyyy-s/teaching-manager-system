package com.gwy.manager.service;

import com.gwy.manager.entity.TeacherAssess;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/1 23:03
 */
public interface TeacherAssessService {

    /**
     * 添加教师评价指标
     * @param teacherAssess 教师评价结果
     * @return  返回行数
     */
    int addTeacherAssess(TeacherAssess teacherAssess);

    /**
     * 获得教师本学期评价的指标
     * @param tno 教师号
     * @param assessedTeacherNo 被评价的教师号
     * @return  返回结果
     */
    TeacherAssess getTeacherAssess(String tno, String assessedTeacherNo, String deptId);

    /**
     * 按学院和学期获得教师评估
     * @param deptId 学院id
     * @param termId 学期id
     * @return  返回列表
     */
    List<TeacherAssess> getTeacherAssessesByTermAndDept(String deptId, String termId);
}
