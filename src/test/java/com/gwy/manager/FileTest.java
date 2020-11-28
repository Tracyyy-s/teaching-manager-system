package com.gwy.manager;

import com.gwy.manager.dto.ExcelSheetPO;
import com.gwy.manager.util.DateUtilCustom;
import com.gwy.manager.util.ExcelUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.Test;
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

    //@Test
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
