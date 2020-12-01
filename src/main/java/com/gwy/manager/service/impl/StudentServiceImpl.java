package com.gwy.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gwy.manager.constant.RoleName;
import com.gwy.manager.entity.User;
import com.gwy.manager.mail.MailForm;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Student;
import com.gwy.manager.mapper.StudentMapper;
import com.gwy.manager.mapper.UserMapper;
import com.gwy.manager.rabbimq.RabbitmqProducer;
import com.gwy.manager.service.StudentService;
import com.gwy.manager.util.BeanUtil;
import com.gwy.manager.mail.MailUtil;
import com.gwy.manager.redis.RedisUtil;
import com.gwy.manager.util.PageHelperUtil;
import com.gwy.manager.util.ResultVOUtil;
import com.gwy.manager.util.VRCodeUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.text.translate.UnicodeUnescaper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author Tracy
 * @date 2020/11/3 16:12
 */
@Service
public class StudentServiceImpl implements StudentService {

    private Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VRCodeUtil vrCodeUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public int addStudent(Student student) {
        return studentMapper.insert(student);
    }

    private Student getStudent(String studentNo) {
        return studentMapper.selectByPrimaryKey(studentNo);
    }

    @Override
    public ResultVO getStudentInfo(String studentNo) {

        ResultVO resultVO = new ResultVO();

        Student student = this.getStudent(studentNo);
        if (student == null) {
            resultVO = ResultVOUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO.success(BeanUtil.beanToMap(student));
        }

        return resultVO;
    }

    @Override
    public ResultVO getStudentInfoByAdmin(String adminNo, String studentNo) {
        ResultVO resultVO = new ResultVO();

        Student student = this.getStudent(studentNo);
        if (student == null) {
            resultVO = ResultVOUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            //获得管理员角色用户
            User adminUser = userMapper.selectByPrimaryKey(adminNo);
            //若学生学院不在管理员可管理学院内
            if (adminUser == null || !adminUser.getAvailableDeptIds().contains(student.getDeptId())) {
                resultVO = ResultVOUtil.error(ResponseDataMsg.PermissionDeny.getMsg());
            } else {
                resultVO.success(BeanUtil.beanToMap(student));
            }
        }

        return resultVO;
    }

    @Transactional
    @Override
    public ResultVO updatePassword(String studentNo, String password, String vrCode) {

        ResultVO resultVO = new ResultVO();

        String code = vrCodeUtil.getCode(studentNo);

        if (!vrCode.equals(code)) {
            resultVO = ResultVOUtil.error("Code Error");
        } else {
            int result = studentMapper.updatePassword(studentNo, passwordEncoder.encode(password));
            if (result == 0) {
                resultVO.setData(ResponseDataMsg.Fail.getMsg());
            } else {
                resultVO.success(ResponseDataMsg.Success.getMsg());
            }

            //修改完毕后删除key
            vrCodeUtil.deleteCode(studentNo);
        }

        return resultVO;
    }

    @Override
    public ResultVO updateStudent(Student student) {

        ResultVO resultVO;

        int i = studentMapper.updateByPrimaryKey(student);
        if (i == 0) {
            resultVO = ResultVOUtil.error(ResponseDataMsg.Fail.getMsg());
        } else {
            resultVO = ResultVOUtil.success(ResponseDataMsg.Success.getMsg());
        }

        return resultVO;
    }

    @Transactional
    @Override
    public ResultVO sendCode(String studentNo) {
        return vrCodeUtil.sendCode(studentNo, RoleName.STUDENT);
    }

    @Override
    public int addStudentBatch(List<Student> students) {
        return studentMapper.insertStudentBatch(students);
    }

    @Override
    public ResultVO getStudentsByDept(String deptId) {

        ResultVO resultVO = new ResultVO();

        List<Student> students = studentMapper.selectStudentsByDept(deptId);
        if (students == null || students.size() == 0) {
            resultVO.setData(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO.success(BeanUtil.beansToList(students));
        }
        return resultVO;
    }

    @Override
    public ResultVO getStudentByClass(String classId) {
        ResultVO resultVO = new ResultVO();

        List<Student> students = studentMapper.selectStudentsByClass(classId);
        if (students.size() == 0) {
            resultVO.setData(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO.success(BeanUtil.beansToList(students));
        }
        return resultVO;
    }

    @Override
    public ResultVO getStudentsMatchName(String adminNo, String deptId, String name) {

        ResultVO resultVO = new ResultVO();

        User adminUser = userMapper.selectByPrimaryKey(adminNo);
        if (adminUser == null || !adminUser.getAvailableDeptIds().contains(deptId)) {
            resultVO = ResultVOUtil.error(ResponseDataMsg.PermissionDeny.getMsg());
            return resultVO;
        }

        List<Student> students = studentMapper.selectStudentsMatchName(deptId, name);
        if (CollectionUtils.isEmpty(students)) {
            resultVO.setData(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO.success(BeanUtil.beansToList(students));
        }

        return resultVO;
    }

    @Override
    public ResultVO getAllStudents(int pageNum, int pageSize) {
        ResultVO resultVO;

        PageHelper.startPage(pageNum, pageSize);
        List<Student> students = studentMapper.selectAll();
        if (CollectionUtils.isEmpty(students)) {
            resultVO = ResultVOUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO = ResultVOUtil.success(PageHelperUtil.pageInfoToMap(new PageInfo<>(students)));
        }

        return resultVO;
    }
}
