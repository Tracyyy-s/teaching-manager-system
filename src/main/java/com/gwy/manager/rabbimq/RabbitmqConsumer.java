package com.gwy.manager.rabbimq;

import com.alibaba.fastjson.JSONObject;
import com.gwy.manager.config.rabbitmq.RabbitmqConfiguration;
import com.gwy.manager.elastic.SysLogString;
import com.gwy.manager.elastic.SysLogStringElasticRepository;
import com.gwy.manager.entity.SysLog;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.mail.MailForm;
import com.gwy.manager.mail.MailUtil;
import com.gwy.manager.service.impl.SysLogServiceImpl;
import com.gwy.manager.util.DateUtilCustom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

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
        } catch (Exception e) {
            logger.error("{}", e.getMessage());
            throw new MessageConversionException(ResponseDataMsg.Fail.getMsg());
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

            //创建线程添加至ElasticSearch中
            new Thread(
                    new ElasticPushThread(
                            new SysLogString(sysLog.getId(),
                                    JSONObject.toJSONStringWithDateFormat(sysLog,
                                            DateUtilCustom.TIME_PATTERN))
            )).start();

        } catch (Exception e) {
            logger.error("{}", e.getMessage());
            throw new MessageConversionException(ResponseDataMsg.Fail.getMsg());
        }
    }

    @Autowired
    private SysLogStringElasticRepository repository;

    /**
     * ElasticSearch的推送线程
     */
    private class ElasticPushThread implements Runnable {

        SysLogString sysLogString;

        public ElasticPushThread(SysLogString sysLogString) {
            this.sysLogString = sysLogString;
        }

        @Override
        public void run() {
            repository.save(sysLogString);
        }
    }
}
