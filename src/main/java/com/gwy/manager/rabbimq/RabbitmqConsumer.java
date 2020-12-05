package com.gwy.manager.rabbimq;

import com.gwy.manager.config.rabbitmq.RabbitmqConfiguration;
import com.gwy.manager.entity.SysLog;
import com.gwy.manager.mail.MailForm;
import com.gwy.manager.mail.MailUtil;
import com.gwy.manager.service.impl.SysLogServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author Tracy
 * @date 2020/11/10 14:20
 */
@Component
public class RabbitmqConsumer {

    private Logger logger = LoggerFactory.getLogger(RabbitmqProducer.class);

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private SysLogServiceImpl logService;

    /**
     * 监听邮件队列，并将监听得到的邮件进行发送
     * @param mailForm  邮件表单体
     */
    @RabbitListener(queues = RabbitmqConfiguration.QUEUE_MAIL)
    public void onMailMessage(MailForm mailForm) {

        MimeMessage mimeMessage = mailUtil.createMimeMessage();

        MimeMessageHelper helper = mailUtil.newMessageHelper(mimeMessage);
        try {
            helper.setSubject(mailForm.getSubject());
            helper.setText(mailForm.getText(), mailForm.isHtml());
            helper.setFrom(mailForm.getFrom());
            helper.setTo(mailForm.getTo());

            mailUtil.sendMimeMail(mimeMessage);
        } catch (MessagingException e) {
            logger.error("{}", e.getMessage());
        }
    }

    /**
     * 监听日志队列，判断是否有日志加入
     * @param sysLog    预添加
     */
    @RabbitListener(queues = RabbitmqConfiguration.QUEUE_LOG)
    public void onLogging(SysLog sysLog) {
        try {
            logService.insertLog(sysLog);
        } catch (Exception e) {
            logger.error("{}", e.getMessage());
        }
    }
}
