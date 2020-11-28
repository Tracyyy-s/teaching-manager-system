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
     * @param studentNo
     * @param tcId
     * @return
     */
    int deleteAppraise(String studentNo, String tcId);

    /**
     * 获取学生对某课程的评价
     * @param sno
     * @param tcId
     * @return
     */
    StudentAssess getStudentAssesses(String sno, String tcId);

    /**
     * 获取学生本学期的所有评价
     * @param sno
     * @param termId
     * @return
     */
    List<StudentAssess> getStudentAssessesByTerm(String sno, String termId);
}
