package com.gwy.manager;

import com.gwy.manager.mail.MailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @author Tracy
 * @date 2020/11/9 16:50
 */
@SpringBootTest
public class MailTest {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailUtil mailUtil;

    @Test
    void test01() {
        System.out.println(mailUtil.getSender());
    }

    @Test
    public void test02() {
        String checkCode = "您好，您的认证码为：892345，5分钟内有效。";
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("lecture4assistant@163.com");
        simpleMailMessage.setSubject("DOBOTO轻聊");
        simpleMailMessage.setText(checkCode);
        simpleMailMessage.setTo(/*userName+*/"771766975@qq.com");
        javaMailSender.send(simpleMailMessage);
    }
}
