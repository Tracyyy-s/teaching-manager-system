package com.gwy.manager.service;

import com.gwy.manager.domain.dto.ResultVo;
import com.gwy.manager.domain.entity.TeacherAssess;

import java.util.List;
import java.util.Map;

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
    ResultVo addTeacherAssess(TeacherAssess teacherAssess);

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

    /**
     * 获得某用户的历史评价
     * @param tno   教师用户id
     * @return  结果集
     */
    ResultVo getHistoryAssessesOfTeacher(String tno);

    /**
     * 获得某学院某学期所有的教师评价及分数
     * @param termId    学期id
     * @param deptId    学院id
     * @return  结果集
     */
    List<Map<String, Object>> getScoresByTermAndDept(String deptId, String termId);
}
