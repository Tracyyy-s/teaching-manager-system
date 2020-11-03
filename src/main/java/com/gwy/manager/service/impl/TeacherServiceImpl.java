package com.gwy.manager.service.impl;

import com.gwy.manager.entity.Teacher;
import com.gwy.manager.mapper.TeacherMapper;
import com.gwy.manager.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/1 23:13
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Teacher getTeacherByTno(String tno) {
        return teacherMapper.selectByPrimaryKey(tno);
    }

    @Override
    public List<Teacher> getTeachersOfDept(String deptId) {
        return teacherMapper.selectByDeptId(deptId);
    }

    @Override
    public int updateTeacher(Teacher teacher) {
        return teacherMapper.updateByPrimaryKey(teacher);
    }

    @Override
    public int addTeachersByBatch(List<Teacher> teachers) {
        return teacherMapper.insertTeachersByBatch(teachers);
    }
}
