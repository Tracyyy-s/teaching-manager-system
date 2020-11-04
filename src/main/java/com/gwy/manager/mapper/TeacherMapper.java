package com.gwy.manager.mapper;

import com.gwy.manager.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper {
    int deleteByPrimaryKey(String teacherNo);

    int insert(Teacher record);

    Teacher selectByPrimaryKey(String teacherNo);

    List<Teacher> selectAll();

    int updateByPrimaryKey(Teacher record);

    List<Teacher> selectByDeptId(String deptId);

    int insertTeachersByBatch(@Param("teachers") List<Teacher> teachers);

    int updatePassword(@Param("teacherNo")String teacherNo, @Param("password") String password);
}