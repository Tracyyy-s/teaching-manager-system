package com.gwy.manager.config.rabbitmq;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author Tracy
 * @date 2020/11/9 23:22
 */
@Configuration
public class RabbitmqConfiguration {

    /**
     * 交换器
     */
    public static final String   EXCHANGE            =     "exchange.tracy";

    /**
     * 邮件队列
     */
    public static final String   QUEUE_MAIL          =     "queue.mail";

    /**
     * 日志队列
     */
    public static final String   QUEUE_LOG           =     "queue.log";

    /**
     * 邮件路由
     */
    public static final String   ROUTING_KEY_MAIL    =     "queue.mail";

    /**
     * 日志路由
     */
    public static final String   ROUTING_KEY_LOG     =     "queue.log";

    /**
     * 配置rabbitmq的转换器
     * @return  转化器
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
