package com.gwy.manager;

import com.cxytiandi.encrypt.util.AesEncryptUtils;
import com.gwy.manager.domain.constant.EncodeConstant;
import com.gwy.manager.domain.entity.Role;
import com.gwy.manager.mapper.PermissionMapper;
import com.gwy.manager.mapper.SysLogMapper;
import com.gwy.manager.util.RedisUtil;
import com.gwy.manager.service.impl.RoleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.*;

/**
 * @author Tracy
 * @date 2020/12/11 21:41
 */
@SpringBootTest
public class ContextTest {

    //@Test
    void test() {
        System.out.println("hello world");
        System.out.println(System.getProperty("java.version"));
    }

    //@Test
    void test1() {
        HashMap<String, String> map = new HashMap<>();
        map.put("hello", "world");
        map.put("hi", null);
        map.putIfAbsent("hi", "nihao");

        System.out.println(map);
    }

    @Autowired
    private RoleServiceImpl roleService;

    //@Test
    void test02() {
        Role role = new Role();
        role.setRoleName("ffff");
        roleService.addRole(role);
    }

    @Autowired
    private PermissionMapper permissionMapper;

    //@Test
    void test03() {
        System.out.println(permissionMapper.selectAllForMap());
    }

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private SysLogMapper logMapper;

    //@Test
    void test04() throws Exception {
        List<Map<Date, Integer>> maps = logMapper.selectLogsInfo();
        for (Map<Date, Integer> map : maps) {
            System.out.println(map);
        }
    }

    @Test
    void test05() throws Exception {

        String s = AesEncryptUtils.aesEncrypt("Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJlY2hpc2FuIiwic3ViIjoiMTAwMDAiLCJpYXQiOjE2MDgyMTY1NTYsImV4cCI6MTYwODI0NTM1Nn0.iF4H80eWwny8O0KLI5sQksDLOQY7jwvTPhypwwiBUN18eXPUOp7lHlVzXpBPHvcucCuv0NFXBjRfFfMXV7x2VQ", EncodeConstant.SALT);
        System.out.println(s);
    }
}
