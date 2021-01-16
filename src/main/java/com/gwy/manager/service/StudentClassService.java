package com.gwy.manager.service;

import com.gwy.manager.domain.entity.StudentClass;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/3 22:12
 */
public interface StudentClassService {

    /**
     * 获得班级信息
     * @param classId
     * @return
     */
    StudentClass getClassById(String classId);

    /**
     * 修改班级信息
     * @param studentClass
     * @return
     */
    int updateClass(StudentClass studentClass);

    /**
     * 添加班级信息
     * @param studentClass
     * @return
     */
    int addClass(StudentClass studentClass);

    /**
     * 获得学院内的所有班级
     * @param deptId
     * @return
     */
    List<StudentClass> getClassesByDept(String deptId);
}
