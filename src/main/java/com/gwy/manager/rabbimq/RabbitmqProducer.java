package com.gwy.manager.rabbimq;

import com.gwy.manager.config.rabbitmq.RabbitmqConfiguration;
import com.gwy.manager.entity.SysLog;
import com.gwy.manager.mail.MailForm;
import com.gwy.manager.mapper.SysLogMapper;
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

    @Autowired
    private SysLogMapper sysLogMapper;

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
