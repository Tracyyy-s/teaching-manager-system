package com.gwy.manager;

import com.gwy.manager.mapper.StudentAssessMapper;
import com.gwy.manager.mapper.TeacherAssessMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
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
}
