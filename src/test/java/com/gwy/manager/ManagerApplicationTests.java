package com.gwy.manager;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.*;
import com.gwy.manager.mapper.*;
import com.gwy.manager.service.impl.TargetServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

@SpringBootTest
class ManagerApplicationTests {

    @Test
    void contextLoads() {

        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int i1 = random.nextInt(10);
            sb.append(i1);
        }
        System.out.println(sb.toString());
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
    void test05() {
        Integer[] ins = new Integer[20];

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        System.out.println(targetService.getTargetsByIds(list));

        Integer[] integers = list.toArray(ins);

        System.out.println(Arrays.toString(integers));
    }

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserMapper userMapper;

    @Test
    void test07() {
        String pattern = "^[0-9a-z]+\\w*@([0-9a-z]+\\.)+[0-9a-z]+$";

        String email = "1111@qq.com";

        boolean matches = Pattern.matches(pattern, email);
        System.out.println(matches);
    }
}
