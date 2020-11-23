package com.gwy.manager.mapper;

import com.gwy.manager.entity.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TeacherMapper {
    int deleteByPrimaryKey(String teacherNo);

    int insert(Teacher record);

    Teacher selectByPrimaryKey(String teacherNo);

    List<Teacher> selectAll();

    int updateByPrimaryKey(Teacher record);

    List<Teacher> selectByDeptId(String deptId);

    List<String> getTeachersByIds(@Param("teacherNos") List<String> teacherNos);

    int insertTeachersByBatch(@Param("teachers") List<Teacher> teachers);

    int updatePassword(@Param("teacherNo")String teacherNo, @Param("password") String password);

    List<Teacher> getTeachersMatchNameInDept(@Param("deptId") String deptId, @Param("name") String name);

    int updateTeacherRole(String teacherNo);
}