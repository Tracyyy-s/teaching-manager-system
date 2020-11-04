package com.gwy.manager.service.impl;

import com.gwy.manager.dto.Response;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Student;
import com.gwy.manager.mapper.StudentMapper;
import com.gwy.manager.service.StudentService;
import com.gwy.manager.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/3 16:12
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public int addStudent(Student student) {
        return studentMapper.insert(student);
    }

    @Override
    public Student getStudent(String studentNo) {
        return studentMapper.selectByPrimaryKey(studentNo);
    }

    @Override
    public ResultVO login(String studentNo, String password) {
        ResultVO resultVO = new ResultVO();

        Student student = this.getStudent(studentNo);

        if (student == null) {
            resultVO.setData("Not Found Student");
        } else if (!MD5Util.inputToDb(password).equals(student.getPassword())) {
            resultVO.setData("Password Incorrect");
        } else {
            resultVO.success("Success");
        }

        return resultVO;
    }

    @Override
    public ResultVO updatePassword(String studentNo, String password) {

        ResultVO resultVO = new ResultVO();

        int result = studentMapper.updatePassword(studentNo, MD5Util.inputToDb(password));
        if (result == 1) {
           resultVO.success("Success");
        } else {
            resultVO.setData("Fail");
        }

        return resultVO;
    }

    @Override
    public int updateStudent(Student student) {
        return studentMapper.updateByPrimaryKey(student);
    }

    @Override
    public int addStudentBatch(List<Student> students) {
        return studentMapper.insertStudentBatch(students);
    }

    @Override
    public List<Student> getStudentsByDept(String deptId) {
        return studentMapper.selectStudentsByDept(deptId);
    }

    @Override
    public List<Student> getStudentByClass(String classId) {
        return studentMapper.selectStudentsByClass(classId);
    }
}
