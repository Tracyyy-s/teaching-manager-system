package com.gwy.manager.service.impl;

import com.gwy.manager.domain.entity.StudentClass;
import com.gwy.manager.mapper.StudentClassMapper;
import com.gwy.manager.service.StudentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/3 22:13
 */
@Service
public class StudentClassServiceImpl implements StudentClassService {

    @Autowired
    private StudentClassMapper studentClassMapper;

    @Override
    public StudentClass getClassById(String classId) {
        return studentClassMapper.selectByPrimaryKey(classId);
    }

    @Override
    public int updateClass(StudentClass studentClass) {
        return studentClassMapper.updateByPrimaryKey(studentClass);
    }

    @Override
    public int addClass(StudentClass studentClass) {
        return studentClassMapper.insert(studentClass);
    }

    @Override
    public List<StudentClass> getClassesByDept(String deptId) {
        return studentClassMapper.selectByDept(deptId);
    }
}
