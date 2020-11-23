package com.gwy.manager.service.impl;

import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.enums.ResponseStatus;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Teacher;
import com.gwy.manager.enums.UserOption;
import com.gwy.manager.mapper.TeacherMapper;
import com.gwy.manager.service.TeacherService;
import com.gwy.manager.util.BeanUtil;
import com.gwy.manager.util.ExcelHeaderFormat;
import com.gwy.manager.util.MD5Util;
import org.apache.commons.beanutils.BeanMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @author Tracy
 * @date 2020/11/1 23:13
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private ExcelHeaderFormat headerFormat;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private Teacher getTeacher(String teacherNo) {
        return teacherMapper.selectByPrimaryKey(teacherNo);
    }

//    @Override
//    public ResultVO login(String teacherNo, String password) {
//
//        ResultVO resultVO = new ResultVO();
//
//        Teacher teacher = teacherMapper.selectByPrimaryKey(teacherNo);
//
//        //未找到教师
//        if (teacher == null) {
//            resultVO.setData(ResponseDataMsg.NotFound.getMsg());
//        } else if (!MD5Util.inputToDb(password).equals(teacher.getPassword())) {
//            //密码错误
//            resultVO.setData(ResponseDataMsg.PasswordIncorrect.getMsg());
//        } else {
//            Map<String, String> map = new HashMap<>();
//            map.put("role", UserOption.TEACHER.getUserType());
//            resultVO.success(map);
//        }
//
//        return resultVO;
//    }


    @Override
    public ResultVO getTeacherInfo(String teacherNo) {

        ResultVO resultVO = new ResultVO();

        Teacher teacher = this.getTeacher(teacherNo);
        if (teacher == null) {
            resultVO.setData(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO.success(BeanUtil.beanToMap(teacher));
        }

        return resultVO;
    }


    @Override
    public ResultVO getTeacherByTnoInDept(String deptId, String tno) {

        ResultVO resultVO = new ResultVO();

        Teacher teacher = teacherMapper.selectByPrimaryKey(tno);

        //未找到教师
        if (teacher == null) {
            resultVO.setData(ResponseDataMsg.NotFound.getMsg());
        } else if (!deptId.equals(teacher.getDeptId())) {
            //教师学院id不等于管理员学院id
            //提示权限不足
            resultVO.setData(ResponseDataMsg.PermissionDeny.getMsg());
        } else {
            //添加教师
            resultVO.success(BeanUtil.beanToMap(teacher));
        }

        return resultVO;
    }

    @Override
    public ResultVO updateTeacher(Teacher teacher) {

        ResultVO resultVO = new ResultVO();

        int i = teacherMapper.updateByPrimaryKey(teacher);
        if (i == 0) {
            resultVO.setData(ResponseDataMsg.Fail.getMsg());
        } else {
            resultVO.success(ResponseDataMsg.Success.getMsg());
        }

        return resultVO;
    }

    @Override
    public ResultVO updatePassword(String teacherNo, String password) {

        ResultVO resultVO = new ResultVO();

        int i = teacherMapper.updatePassword(teacherNo, passwordEncoder.encode(password));
        if (i == 0) {
            resultVO.setData(ResponseDataMsg.Fail.getMsg());
        } else {
            resultVO.success(ResponseDataMsg.Success.getMsg());
        }

        return resultVO;
    }

    @Override
    public ResultVO getTeachersOfDept(String deptId) {

        ResultVO resultVO = new ResultVO();

        //获得学院内的所有教师
        List<Teacher> teachers = teacherMapper.selectByDeptId(deptId);
        //未找到
        if (teachers.size() == 0) {
            resultVO.setData(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO.success(BeanUtil.beansToList(teachers));
        }

        return resultVO;
    }

    @Override
    public ResultVO getTeacherMatchNameInDept(String deptId, String name) {

        ResultVO resultVO = new ResultVO();

        //根据学院号对教师名进行模糊匹配
        List<Teacher> teachers = teacherMapper.getTeachersMatchNameInDept(deptId, name);
        if (teachers.size() == 0) {
            resultVO.setData(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO.success(teachers);
        }

        return resultVO;
    }

    @Transactional(rollbackFor = {Exception.class})
    @SuppressWarnings("unchecked")
    @Override
    public ResultVO importTeachersByFile(String deptId, String headerType, MultipartFile file) {

        ResultVO resultVO = headerFormat.importBeansByFile(deptId, headerType, file);

        if (resultVO.getResultCode().equals(ResponseStatus.SUCCESS.getCode())) {
            Object data = resultVO.getData();
            Map<String, Object> map = (Map<String, Object>)data;

            List<Teacher> teachers = new ArrayList<>();

            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Map<String, Object> dataMap = (Map<String, Object>)entry.getValue();
                teachers.addAll((List<Teacher>)dataMap.get("dataList"));
            }
        }

        return resultVO;
    }

    @Override
    public ResultVO updateTeacherRole(String teacherNo) {

        ResultVO resultVO = new ResultVO();

        int i = teacherMapper.updateTeacherRole(teacherNo);
        if (i == 0) {
            resultVO.setData(ResponseDataMsg.Fail.getMsg());
        } else {
            resultVO.success(ResponseDataMsg.Success.getMsg());
        }

        return resultVO;
    }
}
