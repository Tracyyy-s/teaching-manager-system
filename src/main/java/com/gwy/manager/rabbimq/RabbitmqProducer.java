package com.gwy.manager.rabbimq;

import com.gwy.manager.config.rabbitmq.RabbitmqConfiguration;
import com.gwy.manager.domain.entity.SysLog;
import com.gwy.manager.mail.MailForm;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Tracy
 * @date 2020/11/10 14:07
 */
@Component
public class RabbitmqProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 邮件生产者，将邮件加入mq中
     * @param mailForm  邮件表单
     */
    public void sendMailToMq(MailForm mailForm) {
        String uuid = UUID.randomUUID().toString();

        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(uuid);

        rabbitTemplate.convertAndSend(RabbitmqConfiguration.EXCHANGE,
                RabbitmqConfiguration.ROUTING_KEY_MAIL,
                mailForm,
                correlationData);
    }

    /**
     * 生产者添加日志进入消息队列
     * @param sysLog    日志信息
     */
    public void addLog(SysLog sysLog) {
        String uuid = UUID.randomUUID().toString();

        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(uuid);

        rabbitTemplate.convertAndSend(RabbitmqConfiguration.EXCHANGE,
                RabbitmqConfiguration.ROUTING_KEY_LOG,
                sysLog,
                correlationData);
    }
}
