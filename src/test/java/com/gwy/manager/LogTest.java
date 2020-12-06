package com.gwy.manager;

import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.util.DateUtilCustom;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;

/**
 * @author Tracy
 * @date 2020/12/5 16:25
 */
@SpringBootTest
public class LogTest {

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
    void test02() {
        Date time = DateUtilCustom.getDate();
        System.out.println(time);
        System.out.println(DateUtilCustom.date2String(time));;
    }
}