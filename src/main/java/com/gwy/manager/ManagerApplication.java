package com.gwy.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author TRacy
 */
@MapperScan("com.gwy.manager.mapper")
@EnableCaching
@EnableRabbit
@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication
public class ManagerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(ManagerApplication.class);
//    }
}
