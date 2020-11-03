package com.gwy.manager.service;

import com.gwy.manager.entity.Teacher;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/1 23:02
 */
public interface TeacherService {

    /**
     * 获取教师
     * @param tno 教师tno
     * @return  查询结果
     */
    Teacher getTeacherByTno(String tno);

    /**
     * 获得学院内所有老师
     * @param deptId 学院id
     * @return  学院教师列表
     */
    List<Teacher> getTeachersOfDept(String deptId);

    /**
     * 修改教师信息
     * @param teacher 预修改的教师
     * @return  影响行数
     */
    int updateTeacher(Teacher teacher);

    /**
     * 批量添加教师
     * @param teachers 预添加的教师列表
     * @return  影响行数
     */
    int addTeachersByBatch(List<Teacher> teachers);
}
