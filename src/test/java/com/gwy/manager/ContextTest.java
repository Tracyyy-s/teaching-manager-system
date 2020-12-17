package com.gwy.manager;

import com.gwy.manager.entity.Role;
import com.gwy.manager.mapper.LoginMapper;
import com.gwy.manager.mapper.PermissionMapper;
import com.gwy.manager.redis.RedisUtil;
import com.gwy.manager.service.impl.RoleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.HashMap;
import java.util.Set;

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
    private LoginMapper loginMapper;
    //@Test
    void test04() {
        String s = loginMapper.selectUserPasswordFromAll(" ");
        System.out.println(s);
    }
}
