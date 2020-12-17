package com.gwy.manager.service.impl;

import com.gwy.manager.entity.Student;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.StudentAssess;
import com.gwy.manager.mapper.StudentAssessMapper;
import com.gwy.manager.mapper.StudentMapper;
import com.gwy.manager.mapper.TeacherCourseMapper;
import com.gwy.manager.service.StudentAssessService;
import com.gwy.manager.util.BeanUtil;
import com.gwy.manager.util.DateUtilCustom;
import com.gwy.manager.util.ResultVOUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private StudentMapper studentMapper;

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
        return studentAssessMapper.selectByStudentNoAndTerm(sno, termId);
    }

    @Override
    public ResultVO getStudentAssessesByTcId(String tcId) {

        List<StudentAssess> studentAssesses = studentAssessMapper.selectByTcId(tcId);
        if (CollectionUtils.isEmpty(studentAssesses)) {
            return ResultVOUtil.error(ResponseDataMsg.NotFound.getMsg());
        }

        List<String> studentNos = new ArrayList<>();
        for (StudentAssess studentAssess : studentAssesses) {
            studentNos.add(studentAssess.getStudentNo());
        }

        List<Map<String, Object>> assessMap = ((List<Map<String, Object>>) BeanUtil.beansToList(studentAssesses));

        Map<String, Map<String, String>> nameMap = studentMapper.selectStudentNamesForMapByIds(studentNos);
        for (Map<String, Object> tmpMap : assessMap) {
            String studentNo = (String) tmpMap.get("studentNo");

            if (nameMap.get(studentNo) != null) {
                tmpMap.put("classId", nameMap.get(studentNo).get("class_id"));
                tmpMap.put("studentName", nameMap.get(studentNo).get("student_name"));
            }
        }

        return ResultVOUtil.success(assessMap);
    }

    @Override
    public List<Map<String, Object>> getScoresOfTeachersInTerm(List<String> teacherNos, String termId) {
        return studentAssessMapper.selectByTeacherNosAndTerm(teacherNos, termId);
    }
}
