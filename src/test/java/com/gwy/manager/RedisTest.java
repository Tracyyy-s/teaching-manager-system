package com.gwy.manager;

import com.alibaba.fastjson.JSON;
import com.gwy.manager.entity.Student;
import com.gwy.manager.mapper.StudentMapper;
import com.gwy.manager.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author Tracy
 * @date 2020/11/9 9:30
 */
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private StudentMapper studentMapper;

    @Test
    void test01() {

        Object o = redisUtil.get("stu");
        System.out.println(o);

        Student student = studentMapper.selectByPrimaryKey("2018110114");
        redisUtil.set("stu", student);
    }

    @Test
    void test02() {
        redisUtil.del("hhhh");
    }
}
