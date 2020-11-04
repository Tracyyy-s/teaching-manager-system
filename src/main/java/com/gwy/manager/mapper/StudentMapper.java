package com.gwy.manager.mapper;

import com.gwy.manager.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(String studentNo);

    int insert(Student record);

    Student selectByPrimaryKey(String studentNo);

    List<Student> selectAll();

    int updateByPrimaryKey(Student record);

    /**
     * 批量添加学生
     * @param students
     * @return
     */
    int insertStudentBatch(@Param("students") List<Student> students);

    int updatePassword(@Param("studentNo") String studentNo, @Param("password") String password);

    List<Student> selectStudentsByDept(String deptId);

    List<Student> selectStudentsByClass(String classId);
}