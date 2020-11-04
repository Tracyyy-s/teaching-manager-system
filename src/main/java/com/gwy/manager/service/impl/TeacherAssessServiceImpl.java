package com.gwy.manager.service.impl;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.TeacherAssess;
import com.gwy.manager.mapper.TeacherAssessMapper;
import com.gwy.manager.mapper.TermMapper;
import com.gwy.manager.service.TeacherAssessService;
import com.gwy.manager.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    public ResultVO addTeacherAssess(TeacherAssess teacherAssess) {

        ResultVO resultVO = new ResultVO();

        Date time = DateUtil.getTime();
        teacherAssess.setSubmitTime(time);

        int i = teacherAssessMapper.insert(teacherAssess);
        if (i == 0) {
            resultVO.setData("Fail");
        } else {
            resultVO.success("Success");
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
}
