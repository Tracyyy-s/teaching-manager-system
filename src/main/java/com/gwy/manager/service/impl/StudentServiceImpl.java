package com.gwy.manager.service.impl;

import com.gwy.manager.entity.MailForm;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Student;
import com.gwy.manager.enums.UserOption;
import com.gwy.manager.mapper.StudentMapper;
import com.gwy.manager.rabbimq.RabbitmqProducer;
import com.gwy.manager.service.StudentService;
import com.gwy.manager.util.MD5Util;
import com.gwy.manager.mail.MailUtil;
import com.gwy.manager.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
    private RedisUtil redisUtil;

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private RabbitmqProducer producer;

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
            resultVO.setData(ResponseDataMsg.NotFound.getMsg());
        } else if (!MD5Util.inputToDb(password).equals(student.getPassword())) {
            resultVO.setData(ResponseDataMsg.PasswordIncorrect.getMsg());
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("role", UserOption.STUDENT.getUserType());
            resultVO.success(map);
        }

        return resultVO;
    }

    @Transactional
    @Override
    public ResultVO updatePassword(String studentNo, String password, String vrCode) {

        ResultVO resultVO = new ResultVO();

        String code = (String) redisUtil.get(studentNo + ":Code");

        if (code == null) {
            resultVO.setData("Not Found Code");
        } else if (!vrCode.equals(code)) {
            resultVO.setData("Code Error");
        } else {
            int result = studentMapper.updatePassword(studentNo, MD5Util.inputToDb(password));
            if (result == 0) {
                resultVO.setData(ResponseDataMsg.Fail.getMsg());
            } else {
                resultVO.success(ResponseDataMsg.Success.getMsg());
            }

            //修改完毕后删除key
            redisUtil.del(redisUtil.codeKey(studentNo));
        }

        return resultVO;
    }

    @Override
    public int updateStudent(Student student) {
        return studentMapper.updateByPrimaryKey(student);
    }

    @Transactional
    @Override
    public ResultVO sendCode(String studentNo) {

        ResultVO resultVO = new ResultVO();

        //生成六位验证码
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10));
        }

        //获得学生email，并发送邮件
        String email = studentMapper.selectByPrimaryKey(studentNo).getEmail();

        MailForm mailForm = new MailForm();
        mailForm.setSubject(mailUtil.getSubject());
        mailForm.setText(mailUtil.getPrefix() + code.toString() + mailUtil.getSuffix());
        mailForm.setHtml(true);
        mailForm.setFrom(mailUtil.getSender());
        mailForm.setTo(email);

        //生产者将邮件体发送至rabbitmq
        producer.sendMailToMq(mailForm);

        //设置redis中的key
        String key = redisUtil.codeKey(studentNo);
        try {
            redisUtil.set(key, code.toString());
            redisUtil.expire(key, 300);
        } catch (Exception e) {
            resultVO.setData(ResponseDataMsg.Fail.getMsg());
            return resultVO;
        }

        resultVO.success(ResponseDataMsg.Success.getMsg());
        return resultVO;
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
