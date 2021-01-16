package com.gwy.manager.service.impl;

import com.gwy.manager.domain.dto.ResultVo;
import com.gwy.manager.domain.entity.StudentAssess;
import com.gwy.manager.domain.enums.ResponseDataMsg;
import com.gwy.manager.mapper.StudentAssessMapper;
import com.gwy.manager.mapper.StudentMapper;
import com.gwy.manager.mapper.TeacherCourseMapper;
import com.gwy.manager.service.StudentAssessService;
import com.gwy.manager.util.BeanUtil;
import com.gwy.manager.util.DateUtilCustom;
import com.gwy.manager.util.ResultVoUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public ResultVo addStudentAssess(StudentAssess studentAssess) {

        ResultVo resultVO;

        //判断学生是否提交过
        StudentAssess assess = studentAssessMapper.selectByPrimaryKey(studentAssess.getStudentNo(), studentAssess.getTcId());
        if (assess.getAppraiseScore() != null || assess.getSubmitTime() != null) {
            resultVO = ResultVoUtil.error("Already Assessed!");
        } else {
            if (studentAssess.getAppraiseScore() <= 0) {
                return ResultVoUtil.error("Error Score!");
            }

            //设置评论提交时间
            studentAssess.setSubmitTime(DateUtilCustom.getTime());
            int i = studentAssessMapper.updateByPrimaryKey(studentAssess);

            //修改教师课程评价总分
            int j = teacherCourseMapper.updateAppraiseScore(studentAssess.getTcId(), studentAssess.getAppraiseScore());

            if (i == 0 || j == 0) {
                resultVO = ResultVoUtil.error(ResponseDataMsg.Fail.getMsg());
            } else {
                resultVO = ResultVoUtil.success(ResponseDataMsg.Success.getMsg());
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
    public ResultVo getStudentAssessesByTcId(String tcId) {

        List<StudentAssess> studentAssesses = studentAssessMapper.selectByTcId(tcId);
        if (CollectionUtils.isEmpty(studentAssesses)) {
            return ResultVoUtil.error(ResponseDataMsg.NotFound.getMsg());
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

        return ResultVoUtil.success(assessMap);
    }

    @Override
    public List<Map<String, Object>> getScoresOfTeachersInTerm(List<String> teacherNos, String termId) {
        return studentAssessMapper.selectByTeacherNosAndTerm(teacherNos, termId);
    }
}
