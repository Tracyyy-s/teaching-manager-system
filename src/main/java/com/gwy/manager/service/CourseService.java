package com.gwy.manager.service;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Course;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/1 23:00
 */
public interface CourseService {

    /**
     * 获得课程
     * @param courseNo 课程号courseNo
     * @return  查询结果
     */
    Course getCourse(String courseNo);

    /**
     * 添加课程
     * @param course 预添加
     * @return  影响行数
     */
    int addCourse(Course course);

    /**
     * 修改课程
     * @param course 预修改
     * @return  影响行数
     */
    int updateCourse(Course course);

    /**
     * 获得某教师教授的全部课程
     * @param teacherNo 教师teacherNo
     * @return  结果集
     */
    ResultVO getCoursesOfTeacher(String teacherNo);
}
