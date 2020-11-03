package com.gwy.manager.mapper;

import com.gwy.manager.entity.Course;
import java.util.List;

public interface CourseMapper {
    int deleteByPrimaryKey(String courseNo);

    int insert(Course record);

    Course selectByPrimaryKey(String courseNo);

    List<Course> selectAll();

    int updateByPrimaryKey(Course record);

}