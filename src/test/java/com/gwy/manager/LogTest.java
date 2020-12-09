package com.gwy.manager;

import com.gwy.manager.entity.SysLog;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.enums.SysLogType;
import com.gwy.manager.mapper.SysLogMapper;
import com.gwy.manager.util.DateUtilCustom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Tracy
 * @date 2020/12/5 16:25
 */
@SpringBootTest
public class LogTest {

    @Autowired
    private SysLogMapper logMapper;

    @Test
    void test01() {

        System.out.println(Arrays.toString(ResponseDataMsg.values()));

        int i = 0;
        for (ResponseDataMsg value : ResponseDataMsg.values()) {
            if ("NotFound".equals(value.name())) {
                break;
            }
            i++;
        }
        System.out.println(i);
        System.out.println(ResponseDataMsg.valueOf("NotFound").name());
    }

    @Test
    void test02() throws ParseException {
//        String start = "2020-12-07 16:39:00";
//        String end = "2020-12-08 16:39:00";
//        Date startDate = DateUtilCustom.string2Time(start);
//        Date endDate = DateUtilCustom.string2Time(end);
//        System.out.println(logMapper.selectByInterval(startDate, endDate, SysLogType.All.getType()));

    }

    @Test
    void test03() {

        SysLog sysLog = new SysLog();
        sysLog.setId(1);
        sysLog.setUserId("12");
        sysLog.setAuthorities("1");
        sysLog.setRequestUrl("1");
        sysLog.setIp("1");
        sysLog.setLocale("1");
        sysLog.setResultMessage("2");
        sysLog.setData("$");
        sysLog.setCreateTime(new Date());
        sysLog.setParams("G");
        sysLog.setDataExplain("F");
        sysLog.setType("S");
        sysLog.setTypeExplain("S");

        Class<? extends SysLog> clazz = sysLog.getClass();

        Field[] fields = clazz.getDeclaredFields();
        System.out.println(Arrays.toString(fields));
        for (Field field : fields) {
            String name = field.getName();
            field.setAccessible(true);
            Object o = null;
            try {
                o = field.get(sysLog);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println(name + " " + o);
        }

    }
}
