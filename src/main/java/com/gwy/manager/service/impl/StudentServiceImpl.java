package com.gwy.manager.service.impl;

import com.gwy.manager.entity.Student;
import com.gwy.manager.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tracy
 * @date 2020/11/1 23:10
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public int insertStudent(Student student) {
        return 0;
    }

    @Override
    public int updateStudent(Student student) {
        return 0;
    }

    @Override
    public Student getStudentBySno(String sno) {
        return null;
    }
}
