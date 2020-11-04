package com.gwy.manager.service;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Student;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/3 16:12
 */
public interface StudentService {

    /**
     * 添加学生
     * @param student 预添加
     * @return  影响行数
     */
    int addStudent(Student student);

    /**
     * 通过学号获取学生
     * @param studentNo 学号
     * @return  查找结果
     */
    Student getStudent(String studentNo);

    /**
     * 修改学生信息
     * @param student 预修改
     * @return  影响行数
     */
    int updateStudent(Student student);

    /**
     * 修改学生密码
     * @param studentNo
     * @param password
     * @return
     */
    ResultVO updatePassword(String studentNo, String password);

    /**
     * 批量添加学生
     * @param students 学生列表
     * @return  返回行数
     */
    int addStudentBatch(List<Student> students);

    /**
     * 学生登录
     * @param studentNo
     * @param password
     * @return
     */
    ResultVO login(String studentNo, String password);

    /**
     * 获取某学院的所有学生
     * @param deptId
     * @return
     */
    List<Student> getStudentsByDept(String deptId);

    /**
     * 获取某个班级的所有学生
     * @param classId
     * @return
     */
    List<Student> getStudentByClass(String classId);
}
