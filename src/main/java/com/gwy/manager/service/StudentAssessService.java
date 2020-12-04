package com.gwy.manager.service;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.StudentAssess;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/1 23:03
 */
public interface StudentAssessService {

    /**
     * 添加学生评价
     * @param studentAssess 学生评价
     * @return  影响行数
     */
    ResultVO addStudentAssess(StudentAssess studentAssess);

    /**
     * 删除学生对某课程的评价
     * @param studentNo 学号
     * @param tcId  课程号
     * @return  结果集
     */
    int deleteAppraise(String studentNo, String tcId);

    /**
     * 获取学生对某课程的评价
     * @param sno   学号
     * @param tcId  课程号
     * @return  结果集
     */
    StudentAssess getStudentAssesses(String sno, String tcId);

    /**
     * 获取学生本学期的所有评价
     * @param sno   学号
     * @param termId    学期号
     * @return  结果集
     */
    List<StudentAssess> getStudentAssessesByTerm(String sno, String termId);

    /**
     * 获得某门课的所有学生评价
     * @param tcId  课程id
     * @return  结果集
     */
    ResultVO getStudentAssessesByTcId(String tcId);
}
