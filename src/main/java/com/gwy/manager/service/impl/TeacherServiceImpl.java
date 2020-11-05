package com.gwy.manager.service.impl;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Teacher;
import com.gwy.manager.mapper.TeacherMapper;
import com.gwy.manager.service.TeacherService;
import com.gwy.manager.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    public Teacher getTeacher(String teacherNo) {
        return teacherMapper.selectByPrimaryKey(teacherNo);
    }

    @Override
    public ResultVO getTeacherByTnoInDept(String deptId, String tno) {

        ResultVO resultVO = new ResultVO();

        Teacher teacher = teacherMapper.selectByPrimaryKey(tno);

        if (teacher == null) {
            resultVO.setData("Not Found");
        } else if (!deptId.equals(teacher.getDeptId())) {
            resultVO.setData("Permission Deny");
        } else {
            resultVO.success(teacher);
        }

        return resultVO;
    }

    @Override
    public ResultVO login(String teacherNo, String password) {

        ResultVO resultVO = new ResultVO();

        Teacher teacher = teacherMapper.selectByPrimaryKey(teacherNo);

        if (teacher == null) {
            resultVO.setData("Not Found Teacher");
        } else if (!MD5Util.inputToDb(password).equals(teacher.getPassword())) {
            resultVO.setData("Password Incorrect");
        } else {
            resultVO.success(teacher);
        }
        return resultVO;
    }

    @Override
    public ResultVO updateTeacher(Teacher teacher) {

        ResultVO resultVO = new ResultVO();

        int i = teacherMapper.updateByPrimaryKey(teacher);
        if (i == 0) {
            resultVO.setData("Fail");
        } else {
            resultVO.success("Success");
        }

        return resultVO;
    }

    @Override
    public ResultVO updatePassword(String teacherNo, String password) {

        ResultVO resultVO = new ResultVO();

        int i = teacherMapper.updatePassword(teacherNo, MD5Util.inputToDb(password));
        if (i == 0) {
            resultVO.setData("Fail");
        } else {
            resultVO.success("Success");
        }

        return resultVO;
    }

    @Override
    public ResultVO getTeachersOfDept(String deptId) {

        ResultVO resultVO = new ResultVO();

        List<Teacher> teachers = teacherMapper.selectByDeptId(deptId);
        if (teachers.size() == 0) {
            resultVO.setData("Not Found");
        } else {
            resultVO.success(teachers);
        }

        return resultVO;
    }

    @Override
    public ResultVO getTeacherMatchNameInDept(String deptId, String name) {

        ResultVO resultVO = new ResultVO();

        List<Teacher> teachers = teacherMapper.getTeachersMatchNameInDept(deptId, name);
        if (teachers.size() == 0) {
            resultVO.setData("Not Found");
        } else {
            resultVO.success(teachers);
        }

        return resultVO;
    }

    @Override
    public ResultVO importTeachersByFile(MultipartFile file) {
        ResultVO resultVO = new ResultVO();
        resultVO.success("Get It");
        return resultVO;
    }

    @Override
    public int addTeachersByBatch(List<Teacher> teachers) {
        return teacherMapper.insertTeachersByBatch(teachers);
    }
}
