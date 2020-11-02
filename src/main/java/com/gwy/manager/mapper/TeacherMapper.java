package com.gwy.manager.mapper;

import com.gwy.manager.entity.Teacher;
import java.util.List;

/**
 * @author TRacy
 */
public interface TeacherMapper {
    int deleteByPrimaryKey(String teacherNo);

    int insert(Teacher record);

    Teacher selectByPrimaryKey(String teacherNo);

    List<Teacher> selectAll();

    int updateByPrimaryKey(Teacher record);
}