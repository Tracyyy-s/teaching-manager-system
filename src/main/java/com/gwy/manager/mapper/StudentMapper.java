package com.gwy.manager.mapper;

import com.gwy.manager.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@Component
public interface StudentMapper {
    int deleteByPrimaryKey(String studentNo);

    int insert(Student record);

    Student selectByPrimaryKey(String studentNo);

    List<Student> selectAll();

    int updateByPrimaryKey(Student record);

    /**
     * 批量添加学生
     * @param students  学生列表
     * @return  结果集
     */
    int insertStudentBatch(@Param("students") List<Student> students);

    int updatePassword(@Param("studentNo") String studentNo, @Param("password") String password);

    List<Student> selectStudentsByDept(String deptId);

    List<Student> selectStudentsByClass(String classId);

    List<Student> selectStudentsMatchName(@Param("deptId") String deptId,
                                          @Param("name")String name);

    List<Map<String, Object>> selectStudentNamesByIds(@Param("studentNos") List<String> studentNos);
}