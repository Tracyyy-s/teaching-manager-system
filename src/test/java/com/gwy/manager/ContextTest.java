package com.gwy.manager;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

/**
 * @author Tracy
 * @date 2020/12/11 21:41
 */
@SpringBootTest
public class ContextTest {

    @Test
    void test() {
        System.out.println("hello world");
        System.out.println(System.getProperty("java.version"));
    }

    @Test
    void test1() {
        HashMap<String, String> map = new HashMap<>();
        map.put("hello", "world");
        map.put("hi", "how");
        System.out.println(JSONObject.toJSONString(map));
    }
}
