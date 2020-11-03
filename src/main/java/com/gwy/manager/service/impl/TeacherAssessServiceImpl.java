package com.gwy.manager.service.impl;

import com.gwy.manager.entity.TeacherAssess;
import com.gwy.manager.mapper.TeacherAssessMapper;
import com.gwy.manager.service.TeacherAssessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/3 15:14
 */
@Service
public class TeacherAssessServiceImpl implements TeacherAssessService {

    @Autowired
    private TeacherAssessMapper teacherAssessMapper;


    @Override
    public int addTeacherAssess(TeacherAssess teacherAssess) {
        return teacherAssessMapper.insert(teacherAssess);
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
