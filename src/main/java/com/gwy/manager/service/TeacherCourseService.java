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
     * @param teacherCourses    教师课程
     * @return  返回结果
     */
    int addTeacherCoursesBatch(List<TeacherCourse> teacherCourses);

    /**
     * 获得某学生某学期的课程
     * @param studentNo 学号
     * @param termId    学期号
     * @return  返回结果
     */
    ResultVO getCoursesByStudentAndTerm(String studentNo, String termId);

    /**
     * 获得某教师某学期的课程
     * @param teacherNo 教师号
     * @return  返回结果
     */
    ResultVO getCoursesByTeacherAndTerm(String teacherNo);

    /**
     * 获得某学院某学期开设的所有课程
     * @param deptId    学院id
     * @param termId    学期id
     * @return  结果集
     */
    ResultVO getCoursesByDeptAndTerm(String deptId, String termId);

}
