package com.gwy.manager;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Student;
import com.gwy.manager.entity.Term;
import com.gwy.manager.mapper.StudentMapper;
import com.gwy.manager.mapper.TermMapper;
import com.gwy.manager.service.impl.TargetServiceImpl;
import com.gwy.manager.service.impl.TermServiceImpl;
import com.gwy.manager.util.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
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
        System.out.println(targetService.getTargets("student"));
    }

    @Autowired
    private TermMapper termMapper;

    @Test
    void test02() {
        List<Term> terms = termMapper.selectAll();

        Term term = terms.get(0);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String beginDate = dateFormat.format(term.getBeginDate());

        System.out.println(beginDate);

    }

    @Test
    void test03() {

        Student student = new Student();
        student.setStudentNo("11");
        student.setStudentName("11");
        student.setPassword("11");
        student.setGender(1);
        student.setClassId("11");
        student.setPolitical("11");
        student.setEntryYear("22");

        ResultVO resultVO = new ResultVO();
        resultVO.setData(student);

        System.out.println(resultVO.getData());
    }

    @Test
    void test04() {
        System.out.println(MD5Util.inputToDb("123456"));
    }

    @Test
    void test05() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        System.out.println(targetService.getTargetsByIds(list));
    }
}
