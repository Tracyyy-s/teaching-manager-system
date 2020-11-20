package com.gwy.manager.mapper;

import com.gwy.manager.entity.Course;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CourseMapper {
    int deleteByPrimaryKey(String courseNo);

    int insert(Course record);

    Course selectByPrimaryKey(String courseNo);

    List<Course> selectAll();

    int updateByPrimaryKey(Course record);

    List<Course> getCoursesOfTeacher(String teacherNo);

    List<Course> getCoursesByIds(@Param("courseNos") List<String> courseNos);
}