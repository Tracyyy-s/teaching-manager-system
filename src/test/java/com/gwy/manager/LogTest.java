package com.gwy.manager;

import com.gwy.manager.enums.ResponseDataMsg;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

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
}
