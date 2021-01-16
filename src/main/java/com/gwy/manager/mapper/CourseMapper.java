package com.gwy.manager.mapper;

import com.gwy.manager.domain.entity.Course;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@Component
public interface CourseMapper {
    int deleteByPrimaryKey(String courseNo);

    int insert(Course record);

    Course selectByPrimaryKey(String courseNo);

    List<Course> selectAll();

    int updateByPrimaryKey(Course record);

    List<Course> getCoursesOfTeacher(String teacherNo);

    List<Course> getCoursesByIds(@Param("courseNos") List<String> courseNos);

    @MapKey("courseNo")
    Map<String, Course> getCoursesForMapByIds(@Param("courseNos") List<String> courseNos);
}