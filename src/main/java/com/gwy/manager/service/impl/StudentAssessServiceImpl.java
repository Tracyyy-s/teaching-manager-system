package com.gwy.manager.service.impl;

import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.StudentAssess;
import com.gwy.manager.mapper.StudentAssessMapper;
import com.gwy.manager.mapper.TeacherCourseMapper;
import com.gwy.manager.service.StudentAssessService;
import com.gwy.manager.util.DateUtilCustom;
import com.gwy.manager.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/3 15:36
 */
@Service
public class StudentAssessServiceImpl implements StudentAssessService {

    @Autowired
    private StudentAssessMapper studentAssessMapper;

    @Autowired
    private TeacherCourseMapper teacherCourseMapper;

    @Override
    public ResultVO addStudentAssess(StudentAssess studentAssess) {

        ResultVO resultVO;

        //判断学生是否提交过
        StudentAssess assess = studentAssessMapper.selectByPrimaryKey(studentAssess.getStudentNo(), studentAssess.getTcId());
        if (assess.getAppraiseScore() != null || assess.getSubmitTime() != null) {
            resultVO = ResultVOUtil.error("Already Assessed!");
        } else {
            if (studentAssess.getAppraiseScore() <= 0) {
                return ResultVOUtil.error("Error Score!");
            }

            //设置评论提交时间
            studentAssess.setSubmitTime(DateUtilCustom.getTime());
            int i = studentAssessMapper.updateByPrimaryKey(studentAssess);

            //修改教师课程评价总分
            int j = teacherCourseMapper.updateAppraiseScore(studentAssess.getTcId(), studentAssess.getAppraiseScore());

            if (i == 0 || j == 0) {
                resultVO = ResultVOUtil.error(ResponseDataMsg.Fail.getMsg());
            } else {
                resultVO = ResultVOUtil.success(ResponseDataMsg.Success.getMsg());
            }
        }

        return resultVO;
    }

    @Override
    public int deleteAppraise(String studentNo, String tcId) {
        return studentAssessMapper.deleteByPrimaryKey(studentNo, tcId);
    }

    @Override
    public StudentAssess getStudentAssesses(String sno, String tcId) {
        return studentAssessMapper.selectByPrimaryKey(sno, tcId);
    }

    @Override
    public List<StudentAssess> getStudentAssessesByTerm(String sno, String termId) {
        return studentAssessMapper.getStudentAssessByTerm(sno, termId);
    }
}
