package com.gwy.manager.mapper;

import com.gwy.manager.entity.TeacherCourse;
import java.util.List;

public interface TeacherCourseMapper {
    int deleteByPrimaryKey(String tcId);

    int insert(TeacherCourse record);

    TeacherCourse selectByPrimaryKey(String tcId);

    List<TeacherCourse> selectAll();

    int updateByPrimaryKey(TeacherCourse record);
}