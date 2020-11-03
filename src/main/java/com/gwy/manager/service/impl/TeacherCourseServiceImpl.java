package com.gwy.manager.service.impl;

import com.gwy.manager.entity.Course;
import com.gwy.manager.entity.TeacherCourse;
import com.gwy.manager.mapper.TeacherCourseMapper;
import com.gwy.manager.service.TeacherCourseService;
import com.gwy.manager.service.TeacherService;
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
    public List<TeacherCourse> getCoursesByTeacherAndTerm(String teacherNo, String termId) {
        return teacherCourseMapper.getTeacherCoursesByTeacherNoAndTermId(teacherNo, termId);
    }

    @Override
    public List<TeacherCourse> getCoursesByStudentAndTerm(String studentNo, String termId) {
        return teacherCourseMapper.getTeacherCourseByStudentNoAndTermId(studentNo, termId);
    }

}
