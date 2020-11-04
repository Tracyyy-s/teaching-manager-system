package com.gwy.manager.service;

import com.gwy.manager.dto.ResultVO;
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
     * 修改教师信息
     * @param teacher 预修改的教师
     * @return  影响行数
     */
    ResultVO updateTeacher(Teacher teacher);

    /**
     * 修改教师密码
     * @param teacherNo 教师号
     * @return  结果集
     */
    ResultVO updatePassword(String teacherNo, String password);

    /**
     * 教师登录
     * @param teacherNo 教师号
     * @param password  密码
     * @return  返回结果
     */
    ResultVO login(String teacherNo, String password);

    /**
     * 获得学院内所有老师
     * @param deptId 学院id
     * @return  学院教师列表
     */
    List<Teacher> getTeachersOfDept(String deptId);

    /**
     * 批量添加教师
     * @param teachers 预添加的教师列表
     * @return  影响行数
     */
    int addTeachersByBatch(List<Teacher> teachers);
}
