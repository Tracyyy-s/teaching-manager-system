package com.gwy.manager;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.gwy.manager.entity.SysLog;
import com.gwy.manager.util.DateUtilCustom;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Tracy
 * @date 2020/12/18 15:40
 */
@SpringBootTest
public class SeriaTest {

    @Test
    void test() {
        SysLog sysLog = new SysLog();
        sysLog.setId(0);
        sysLog.setUserId("");
        sysLog.setAuthorities("");
        sysLog.setRequestUrl("");
        sysLog.setIp("");
        sysLog.setLocale("");
        sysLog.setResultMessage("");
        sysLog.setData("");
        sysLog.setCreateTime(new Date());
        sysLog.setParams("");
        sysLog.setDataExplain("");
        sysLog.setType("");
        sysLog.setTypeExplain("");

        sysLog.setId(1);
        sysLog.setIp("1");

        String s = JSONObject.toJSONStringWithDateFormat(sysLog, DateUtilCustom.TIME_PATTERN);
        System.out.println(s);

        Map<String, String> map = JSONObject.parseObject(s, Map.class);
        System.out.println(map);
        HashMap<String, Object> hash = new HashMap<>();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String d = entry.getKey().replace("d", "<p>" + "d" + "</p>");
            hash.put(d, entry.getValue());
        }

        System.out.println(hash);
    }
}
