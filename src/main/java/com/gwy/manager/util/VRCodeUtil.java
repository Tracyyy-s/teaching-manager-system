package com.gwy.manager.util;

import com.gwy.manager.constant.RoleName;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Student;
import com.gwy.manager.entity.User;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.enums.UserOption;
import com.gwy.manager.mail.MailForm;
import com.gwy.manager.mail.MailUtil;
import com.gwy.manager.mapper.StudentMapper;
import com.gwy.manager.mapper.UserMapper;
import com.gwy.manager.rabbimq.RabbitmqProducer;
import com.gwy.manager.redis.RedisUtil;
import com.gwy.manager.security.GlobalPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author Tracy
 * @date 2020/11/26 15:26
 */
@Component
public class VRCodeUtil {

    private static final int VR_CODE_LENGTH = 6;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private RabbitmqProducer producer;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private GlobalPasswordEncoder passwordEncoder;

    /**
     * 获得用户验证码的key
     * @param userId    用户id
     * @return  结果集
     */
    public String getCode(String userId) {
        return (String) redisUtil.get(userId + ":Code");
    }

    /**
     * 删除用户
     * @param userId    用户id
     */
    public void deleteCode(String userId) {
        redisUtil.del(redisUtil.codeKey(userId));
    }

    /**
     * 向指定用户发送验证码
     * @param userId    用户id
     * @param userType  用户类型
     * @return  结果集
     */
    public ResultVO sendCode(String userId, String userType) {

        //生成六位验证码
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < VR_CODE_LENGTH; i++) {
            code.append(random.nextInt(10));
        }

        //获得email，并发送邮件
        String email;
        if (userType == null) {
            Student student = studentMapper.selectByPrimaryKey(userId);
            if (student == null) {
                User user = userMapper.selectByPrimaryKey(userId);

                if (user == null) {
                    return ResultVOUtil.error(ResponseDataMsg.NotFound.getMsg());
                }

                email = user.getEmail();
            } else {
                email = student.getEmail();
            }
        } else if (userType.equals(RoleName.USER)) {
            email = userMapper.selectByPrimaryKey(userId).getEmail();
        } else {
            email = studentMapper.selectByPrimaryKey(userId).getEmail();
        }

        MailForm mailForm = new MailForm();
        mailForm.setSubject(mailUtil.getSubject());
        mailForm.setText(mailUtil.getPrefix() + code.toString() + mailUtil.getSuffix());
        mailForm.setHtml(true);
        mailForm.setFrom(mailUtil.getSender());
        mailForm.setTo(email);

        //生产者将邮件体发送至rabbitmq
        producer.sendMailToMq(mailForm);

        //设置redis中的key
        String key = redisUtil.codeKey(userId);
        try {
            redisUtil.set(key, code.toString());
            redisUtil.expire(key, 300);
        } catch (Exception e) {
            return ResultVOUtil.error(ResponseDataMsg.Fail.getMsg());
        }

        if (userType == null) {
            return ResultVOUtil.success(email);
        }

        return ResultVOUtil.success(ResponseDataMsg.Success.getMsg());
    }

    /**
     * 使用code更新密码
     *
     * @param userType 用户类型
     * @param userId   用户id
     * @param password 密码
     * @param vrCode   验证码
     * @return 结果集
     */
    public ResultVO updatePasswordByCode(String userType,
                                         String userId,
                                         String password,
                                         String vrCode) {

        String code = this.getCode(userId);
        if (!vrCode.equals(code)) {
            return ResultVOUtil.error("Code Error");
        }

        ResultVO resultVO;

        int result;
        if (userType.equals(UserOption.STUDENT.getUserType())) {
            result = studentMapper.updatePassword(userId, passwordEncoder.encode(password));
        } else {
            result = userMapper.updatePassword(userId, passwordEncoder.encode(password));
        }

        if (result == 0) {
            resultVO = ResultVOUtil.error(ResponseDataMsg.Fail.getMsg());
        } else {
            resultVO = ResultVOUtil.success(ResponseDataMsg.Success.getMsg());
        }

        //修改完毕后删除key
        this.deleteCode(userId);

        return resultVO;
    }
}
