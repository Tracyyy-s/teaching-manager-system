package com.gwy.manager;

import ch.qos.logback.core.util.FileUtil;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.*;
import com.gwy.manager.mapper.*;
import com.gwy.manager.service.impl.RootServiceImpl;
import com.gwy.manager.service.impl.TargetServiceImpl;
import com.gwy.manager.util.BeanUtil;
import com.gwy.manager.util.MD5Util;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    void test04() {
        System.out.println(MD5Util.inputToDb("123456"));
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
    private DeptMapper deptMapper;

    @Test
    void test06() {
        Map<String, String> map = null;
        try {
            map = BeanUtils.describe(new Teacher());
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        map.remove("class");
        map.remove("password");


        System.out.println(map);
    }

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private RootServiceImpl rootService;

    @Test
    void test07() {
        System.out.println(rootService.login(null, null).getData());
    }
}
