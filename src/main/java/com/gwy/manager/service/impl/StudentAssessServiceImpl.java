package com.gwy.manager.service.impl;

import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.StudentAssess;
import com.gwy.manager.mapper.StudentAssessMapper;
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

    @Override
    public ResultVO addStudentAssess(StudentAssess studentAssess) {

        ResultVO resultVO;

        if (studentAssessMapper.selectByPrimaryKey(studentAssess.getStudentNo(),
                studentAssess.getTcId()) != null) {

            resultVO = ResultVOUtil.error("Already Exist!");
        } else {
            //设置评论提交时间
            studentAssess.setSubmitTime(DateUtilCustom.getTime());
            int i = studentAssessMapper.insert(studentAssess);
            if (i == 0) {
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
