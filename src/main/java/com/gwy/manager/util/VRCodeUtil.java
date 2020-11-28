package com.gwy.manager.util;

import com.gwy.manager.constant.RoleName;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.mail.MailForm;
import com.gwy.manager.mail.MailUtil;
import com.gwy.manager.mapper.StudentMapper;
import com.gwy.manager.mapper.UserMapper;
import com.gwy.manager.rabbimq.RabbitmqProducer;
import com.gwy.manager.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author Tracy
 * @date 2020/11/26 15:26
 */
@Component
public class VRCodeUtil {

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

    public String getCode(String userId) {
        return  (String) redisUtil.get(userId + ":Code");
    }

    public void deleteCode(String userId) {
        redisUtil.del(redisUtil.codeKey(userId));
    }

    public ResultVO sendCode(String userId, String userType) {
        ResultVO resultVO;

        //生成六位验证码
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append(random.nextInt(10));
        }

        //获得email，并发送邮件
        String email = null;
        if (userType.equals(RoleName.USER)) {
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
            resultVO = ResultVOUtil.error(ResponseDataMsg.Fail.getMsg());
            return resultVO;
        }

        resultVO = ResultVOUtil.success(ResponseDataMsg.Success.getMsg());
        return resultVO;
    }
}
