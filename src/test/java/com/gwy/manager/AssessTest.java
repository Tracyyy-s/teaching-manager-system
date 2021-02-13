package com.gwy.manager;

import com.gwy.manager.domain.entity.Dept;
import com.gwy.manager.mapper.StudentAssessMapper;
import com.gwy.manager.mapper.TeacherAssessMapper;
import com.gwy.manager.security.GlobalPasswordEncoder;
import com.gwy.manager.util.BeanUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/12/13 14:43
 */
@SpringBootTest
public class AssessTest {

    @Autowired
    private TeacherAssessMapper teacherAssessMapper;

    @Autowired
    private StudentAssessMapper studentAssessMapper;

    @Test
    void test01() {
        List<Map<String, Object>> maps = teacherAssessMapper.selectByTermAndDept("110", "2020-2021-1");

        List<String> list = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            list.add(((String) map.get("teacherNo")));
        }

        List<Map<String, Object>> sList = studentAssessMapper.selectByTeacherNosAndTerm(list, "2020-2021-1");

        System.out.println(maps);
        System.out.println(sList);
    }

    @Autowired
    GlobalPasswordEncoder passwordEncoder;

    @Test
    void test02() throws Exception {
        HashMap<String, Object> map = new HashMap<>();

        map.put("deptId", "111");
        map.put("deptName", "222");

        Dept dept = BeanUtil.mapToObject(map, Dept.class);
        System.out.println(dept);
    }

    @Test
    public void test04() throws ClassNotFoundException {

        Class<?> aClass = Class.forName("com.gwy.manager.domain.entity.Dept");

        Class<?> bClass = ClassLoader.getSystemClassLoader().loadClass("com.gwy.manager.domain.entity.Dept");

        Dept dept = new Dept();
    }
}
