package com.gwy.manager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class ManagerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ApplicationContext ioc;

    @Test
    void test01() {
        System.out.println(ioc.containsBean("studentServiceImpl"));
    }

}
