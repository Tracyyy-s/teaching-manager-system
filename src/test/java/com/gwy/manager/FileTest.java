package com.gwy.manager;

import com.gwy.manager.entity.ExcelSheetPO;
import com.gwy.manager.entity.Teacher;
import com.gwy.manager.service.impl.TeacherServiceImpl;
import com.gwy.manager.util.DateUtilCustom;
import com.gwy.manager.util.ExcelUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.*;

/**
 * @author Tracy
 * @date 2020/11/6 23:34
 */
@SpringBootTest

public class FileTest {

    @Test
    void test01() throws IOException, ParseException {

        File file = new File("C:\\Users\\TRacy\\Desktop\\test.xlsx");

        List<ExcelSheetPO> result = ExcelUtil.readExcel("", file);

        List<List<Object>> dataList = result.get(0).getDataList();

        String[] headers = result.get(0).getHeaders();

        System.out.println(Arrays.toString(headers));

        List<Object> obj = dataList.get(0);

        System.out.println(obj);

        Map<String, Object> map = new LinkedHashMap<>();

        map.put("teacherNo", obj.get(0));
        map.put("teacherName", obj.get(1));
        map.put("gender", obj.get(2).equals("男") ? 1 : obj.get(2).equals("女") ? 0 : "null");
        map.put("password", obj.get(3));
        map.put("birth", DateUtilCustom.string2Date((String) obj.get(4)));
        map.put("degree", obj.get(5));
        map.put("graduated", obj.get(6));
        map.put("political", obj.get(7));
        map.put("entryYear", obj.get(8));
        map.put("deptId", obj.get(9));
        map.put("professional", obj.get(10));
        map.put("professionalTime", DateUtilCustom.string2Date((String) obj.get(11)));

        Teacher teacher = new Teacher();
        try {
            BeanUtils.populate(teacher, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println(teacher);
    }

    @Test
    void test02() throws IOException {
        File file = new File("D:\\QQfiles\\3班选修统计.xlsx");

        List<ExcelSheetPO> pos = ExcelUtil.readExcel("", file);

        List<List<Object>> list = pos.get(0).getDataList();

        List<String> data = new ArrayList<>();
        for (List<Object> objects : list) {
            data.add(objects.get(1).toString());
        }

        System.out.println(data);

        String dirPath = "E:\\_before";

        File file1 = new File(dirPath);

        File[] files = file1.listFiles();

        List<String> nowFiles = new ArrayList<>();
        assert files != null;
        for (File file2 : files) {
            String name = file2.getName();

            String[] s = name.split("_");


            nowFiles.add(s[2].split("\\.")[0]);
        }

        System.out.println(nowFiles);

        List<String> noSubmit = new ArrayList<>();

        for (String datum : data) {
            boolean b = nowFiles.contains(datum);
            if (!b) {
                noSubmit.add(datum);
            }
        }

        System.out.println(noSubmit);
    }
}
