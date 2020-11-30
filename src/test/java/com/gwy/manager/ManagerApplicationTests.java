package com.gwy.manager;

import com.alibaba.fastjson.JSONObject;
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
        String a = "{\"data\":[{\"userId\":\"10000\",\"username\":\"root\",\"gender\":1},{\"userId\":\"2001110002\",\"username\":\"唐一\",\"gender\":1,\"email\":\"291434953@qq.com\",\"birth\":\"2020-11-05\",\"degree\":\"博士\",\"graduated\":\"清华大学\",\"political\":\"党员\",\"entryYear\":2011,\"sumHour\":582,\"deptId\":\"110\",\"professional\":\"教授\",\"professionalTime\":\"2020-11-29\",\"availableDeptIds\":\"110,\"},{\"userId\":\"2001110003\",\"username\":\"华南\",\"gender\":1,\"email\":\"291434953@qq.com\",\"birth\":\"2020-11-29\",\"degree\":\"博士\",\"graduated\":\"清华大学\",\"political\":\"党员\",\"entryYear\":2011,\"sumHour\":582,\"deptId\":\"110\",\"professional\":\"教授\",\"professionalTime\":\"2020-11-26\",\"availableDeptIds\":\"110,\"},{\"userId\":\"2001110004\",\"username\":\"陈北\",\"gender\":1,\"email\":\"291434953@qq.com\",\"birth\":\"2020-11-29\",\"degree\":\"博士\",\"graduated\":\"清华大学\",\"political\":\"党员\",\"entryYear\":2011,\"sumHour\":582,\"deptId\":\"110\",\"professional\":\"教授\",\"professionalTime\":\"2020-11-05\",\"availableDeptIds\":\"110,\"},{\"userId\":\"200411010\",\"username\":\"陈志伟\",\"gender\":1,\"email\":\"291434953@qq.com\",\"birth\":\"1980-03-03\",\"degree\":\"硕士\",\"graduated\":\"四川大学\",\"political\":\"党员\",\"entryYear\":2005,\"sumHour\":640,\"deptId\":\"110\",\"professional\":\"教授\",\"professionalTime\":\"2008-10-10\",\"availableDeptIds\":\"110,111,\"},{\"userId\":\"200511003\",\"username\":\"张东山\",\"gender\":1,\"email\":\"291434953@qq.com\",\"birth\":\"1970-01-01\",\"degree\":\"博士\",\"graduated\":\"四川大学\",\"political\":\"党员\",\"entryYear\":2005,\"sumHour\":720,\"deptId\":\"110\",\"professional\":\"教授\",\"professionalTime\":\"2010-11-11\",\"availableDeptIds\":\"110,111,112,\"},{\"userId\":\"200611044\",\"username\":\"王新梅\",\"gender\":0,\"email\":\"291434953@qq.com\",\"birth\":\"1975-12-02\",\"degree\":\"博士\",\"graduated\":\"电子科技大学\",\"political\":\"党员\",\"entryYear\":2006,\"sumHour\":640,\"deptId\":\"110\",\"professional\":\"副教授\",\"professionalTime\":\"2009-11-11\",\"availableDeptIds\":\"110,\"},{\"userId\":\"200711024\",\"username\":\"李辉鹏\",\"gender\":1,\"email\":\"291434953@qq.com\",\"birth\":\"1980-03-03\",\"degree\":\"硕士\",\"graduated\":\"上海交通大学\",\"political\":\"党员\",\"entryYear\":2007,\"sumHour\":490,\"deptId\":\"110\",\"professional\":\"副教授\",\"professionalTime\":\"2012-11-11\",\"availableDeptIds\":\"110,\"},{\"userId\":\"201011055\",\"username\":\"周福清\",\"gender\":1,\"email\":\"291434953@qq.com\",\"birth\":\"1975-10-25\",\"degree\":\"博士\",\"graduated\":\"清华大学\",\"political\":\"党员\",\"entryYear\":2010,\"sumHour\":569,\"deptId\":\"110\",\"professional\":\"教授\",\"professionalTime\":\"2005-10-10\",\"availableDeptIds\":\"110,\"},{\"userId\":\"201011056\",\"username\":\"李敏\",\"gender\":0,\"email\":\"291434953@qq.com\",\"birth\":\"1975-10-26\",\"degree\":\"博士\",\"graduated\":\"清华大学\",\"political\":\"党员\",\"entryYear\":2011,\"sumHour\":582,\"deptId\":\"110\",\"professional\":\"教授\",\"professionalTime\":\"2005-10-11\",\"availableDeptIds\":\"110,\"}],\"message\":\"SUCCESS\",\"resultCode\":1}";
        ResultVO resultVO = JSONObject.parseObject(a, ResultVO.class);
        System.out.println(resultVO.getResultCode());
        System.out.println(resultVO.getMessage());
        System.out.println(resultVO.getData());
    }

    @Test
    void decode() {
        String a = "123456";
        System.out.println(passwordEncoder.encode(a));;
    }
}
