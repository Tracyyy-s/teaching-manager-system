package com.gwy.manager;

import com.gwy.manager.entity.TestTable;
import com.gwy.manager.mapper.TermMapper;
import com.gwy.manager.mapper.TestTableMapper;
import com.gwy.manager.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/4 19:07
 */
@SpringBootTest
public class DateTest {

    @Autowired
    private TestTableMapper testTableMapper;

    @Test
    void test01() {

        TestTable testTable = new TestTable();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        testTable.setDate(new Date());
        testTable.setDateTime(new Timestamp(new Date().getTime()));
        testTableMapper.insert(testTable);
    }

    @Test
    void test02() {

        List<TestTable> tests = testTableMapper.selectAll();

        TestTable table = tests.get(1);

        System.out.println(DateUtil.date2String(table.getDateTime()));

        System.out.println(DateUtil.getTime());

        System.out.println(new Date().getTime());
    }

    @Test
    void test03() {

        Date date = new Date();

        try {
            Thread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Date date1 = new Date();

        System.out.println();
    }

    @Autowired
    private TermMapper termMapper;

    @Test
    void test04() {
        System.out.println(termMapper.getCurrentTerm(new Date()));
    }
}
