package com.gwy.manager.service;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.TeacherCourse;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/3 14:51
 */
public interface TeacherCourseService {

    /**
     * 添加教师教授课程
     * @param teacherCourse
     * @return
     */
    int addTeacherCourse(TeacherCourse teacherCourse);

    /**
     * 修改教师课程评分
     * @param tcId
     * @param changeScore
     * @return
     */
    int changeTeacherCourseScore(String tcId, Integer changeScore);

    /**
     * 批量添加教师-课程信息
     * @param teacherCourses
     * @return
     */
    int addTeacherCoursesBatch(List<TeacherCourse> teacherCourses);

    /**
     * 获得某学生某学期的课程
     * @param studentNo
     * @param termId
     * @return
     */
    ResultVO getCoursesByStudentAndTerm(String studentNo, String termId);

    /**
     * 获得某教师某学期的课程
     * @param teacherNo
     * @return
     */
    ResultVO getCoursesByTeacherAndTerm(String teacherNo);
}
