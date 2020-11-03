package com.gwy.manager.service.impl;

import com.gwy.manager.entity.StudentAssess;
import com.gwy.manager.mapper.StudentAssessMapper;
import com.gwy.manager.service.StudentAssessService;
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

    @Override
    public int addStudentAssess(StudentAssess studentAssess) {
        return studentAssessMapper.insert(studentAssess);
    }

    @Override
    public int deleteAppraise(String studentNo, String tcId) {
        return studentAssessMapper.deleteByPrimaryKey(studentNo, tcId);
    }

    @Override
    public int updateAppraise(StudentAssess studentAssess) {
        return studentAssessMapper.updateByPrimaryKey(studentAssess);
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
