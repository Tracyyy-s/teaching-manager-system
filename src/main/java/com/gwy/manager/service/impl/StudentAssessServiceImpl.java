package com.gwy.manager.service.impl;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.StudentAssess;
import com.gwy.manager.mapper.StudentAssessMapper;
import com.gwy.manager.service.StudentAssessService;
import com.gwy.manager.util.DateUtil;
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
    public ResultVO updateAppraise(StudentAssess studentAssess) {

        ResultVO resultVO = new ResultVO();

        //设置评论提交时间
        studentAssess.setSubmitTime(DateUtil.getTime());
        int i = studentAssessMapper.updateByPrimaryKey(studentAssess);
        if (i == 0) {
            resultVO.setData("Fail");
        } else {
            resultVO.success("Success");
        }

        return resultVO;
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
