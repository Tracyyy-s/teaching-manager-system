package com.gwy.manager.service;

import com.gwy.manager.entity.Student;

/**
 * @author Tracy
 * @date 2020/11/1 23:01
 */
public interface StudentService {

    /**
     * 添加学生
     * @param student 预添加的学生
     * @return  返回行数
     */
    int insertStudent(Student student);

    /**
     * 修改学生
     * @param student 预修改的学生
     * @return  影响行数
     */
    int updateStudent(Student student);

    /**
     * 查找学生
     * @param sno 学号
     * @return  查询结果
     */
    Student getStudentBySno(String sno);
}
