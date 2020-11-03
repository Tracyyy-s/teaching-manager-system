package com.gwy.manager;

import com.gwy.manager.entity.Term;
import com.gwy.manager.mapper.StudentMapper;
import com.gwy.manager.service.impl.TargetServiceImpl;
import com.gwy.manager.service.impl.TermServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;

@SpringBootTest
class ManagerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ApplicationContext ioc;

    @Autowired
    private TargetServiceImpl targetService;

    @Test
    void test01() {
        System.out.println(targetService.getStudentTargets());
    }

    @Autowired
    private TermServiceImpl termService;

    @Test
    void test02() {
        List<Term> terms = termService.getTerms();

        Term term = terms.get(0);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String beginDate = dateFormat.format(term.getBeginDate());

        System.out.println(beginDate);

    }
}
