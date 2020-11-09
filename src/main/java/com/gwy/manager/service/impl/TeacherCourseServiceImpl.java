package com.gwy.manager.service.impl;

import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.TeacherCourse;
import com.gwy.manager.mapper.TeacherCourseMapper;
import com.gwy.manager.mapper.TermMapper;
import com.gwy.manager.service.TeacherCourseService;
import com.gwy.manager.util.DateUtilCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/1 23:13
 */
@Service
public class TeacherCourseServiceImpl implements TeacherCourseService {

    @Autowired
    private TeacherCourseMapper teacherCourseMapper;

    @Autowired
    private TermMapper termMapper;

    @Override
    public int addTeacherCourse(TeacherCourse teacherCourse) {
        return teacherCourseMapper.insert(teacherCourse);
    }

    @Override
    public int changeTeacherCourseScore(String tcId, Integer changeScore) {
        return teacherCourseMapper.updateAppraiseScore(tcId, changeScore);
    }

    @Override
    public int addTeacherCoursesBatch(List<TeacherCourse> teacherCourses) {
        return teacherCourseMapper.insertBatch(teacherCourses);
    }

    @Override
    public ResultVO getCoursesByTeacherAndTerm(String teacherNo) {

        ResultVO resultVO = new ResultVO();

        //获得当前学期
        String currentTerm = termMapper.getCurrentTerm(DateUtilCustom.getDate());
        try {
            List<TeacherCourse> courses = teacherCourseMapper
                    .getTeacherCoursesByTeacherNoAndTermId(teacherNo, currentTerm);

            if (courses.size() == 0) {
                resultVO.setData(ResponseDataMsg.NotFound.getMsg());
            } else {
                resultVO.success(courses);
            }
        } catch (Exception e) {
            resultVO.setData(ResponseDataMsg.Fail.getMsg());
        }

        return resultVO;
    }

    @Override
    public ResultVO getCoursesByStudentAndTerm(String studentNo, String termId) {

        ResultVO resultVO = new ResultVO();

        List<TeacherCourse> teacherCourses = teacherCourseMapper
                .getTeacherCourseByStudentNoAndTermId(studentNo, termId);

        if (teacherCourses.size() == 0) {
            resultVO.setData(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO.success(teacherCourses);
        }

        return resultVO;
    }

}
