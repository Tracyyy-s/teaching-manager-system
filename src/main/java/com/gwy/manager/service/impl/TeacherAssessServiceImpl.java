package com.gwy.manager.service.impl;

import com.gwy.manager.domain.dto.ResultVo;
import com.gwy.manager.domain.enums.ResponseDataMsg;
import com.gwy.manager.domain.entity.TeacherAssess;
import com.gwy.manager.mapper.TeacherAssessMapper;
import com.gwy.manager.mapper.TermMapper;
import com.gwy.manager.service.TeacherAssessService;
import com.gwy.manager.util.DateUtilCustom;
import com.gwy.manager.util.ResultVoUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/3 15:14
 */
@Service
public class TeacherAssessServiceImpl implements TeacherAssessService {

    @Autowired
    private TeacherAssessMapper teacherAssessMapper;

    @Autowired
    private TermMapper termMapper;

    @Override
    public ResultVo addTeacherAssess(TeacherAssess teacherAssess) {

        ResultVo resultVO;

        //如果该教师在本学期已经评价过某教师
        if (teacherAssessMapper.selectByPrimaryKey(teacherAssess.getTeacherNo(),
                teacherAssess.getAssessedTeacherNo(), teacherAssess.getTermId()) != null) {

            resultVO = ResultVoUtil.error("Already Exist");
        } else {
            teacherAssess.setSubmitTime(DateUtilCustom.getTime());

            int i = teacherAssessMapper.insert(teacherAssess);
            if (i == 0) {
                resultVO = ResultVoUtil.error(ResponseDataMsg.Fail.getMsg());
            } else {
                resultVO = ResultVoUtil.success(ResponseDataMsg.Success.getMsg());
            }
        }

        return resultVO;
    }

    @Override
    public TeacherAssess getTeacherAssess(String tno, String assessedTeacherNo, String deptId) {
        return teacherAssessMapper.selectByPrimaryKey(tno, assessedTeacherNo, deptId);
    }

    @Override
    public List<TeacherAssess> getTeacherAssessesByTermAndDept(String deptId, String termId) {
        return teacherAssessMapper.getTeacherAssessesByDeptAndTerm(deptId, termId);
    }

    @Override
    public ResultVo getHistoryAssessesOfTeacher(String tno) {

        ResultVo resultVO;

        List<TeacherAssess> teacherAssesses = teacherAssessMapper.selectAllByTno(tno);
        if (CollectionUtils.isEmpty(teacherAssesses)) {
            resultVO = ResultVoUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO = ResultVoUtil.success(teacherAssesses);
        }

        return resultVO;
    }

    @Override
    public List<Map<String, Object>> getScoresByTermAndDept(String deptId, String termId) {

        return teacherAssessMapper.selectByTermAndDept(deptId, termId);
    }
}
