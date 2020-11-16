package com.gwy.manager.util;

import com.google.common.collect.Maps;
import org.springframework.cglib.beans.BeanMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/16 19:39
 */
public class BeanUtil {

    /**
     * 将对象转换为map
     * @param bean 对象
     * @return  返回值
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                if (key.equals("password"))
                    continue;
                map.put(key.toString(), beanMap.get(key));
            }
        }

        return map;
    }

    /**
     * 将beans转为list
     * @param beans 对象
     * @return  返回值
     */
    public static <T> Collection<Map<String, Object>> beansToList(Collection<T> beans) {

        ArrayList<Map<String, Object>> list = new ArrayList<>();
        if (beans != null) {
            for (T bean : beans) {
                Map<String, Object> map = Maps.newHashMap();
                BeanMap beanMap = BeanMap.create(bean);

                for (Object key : beanMap.keySet()) {
                    if (key.equals("password"))
                        continue;
                    map.put(key.toString(), beanMap.get(key));
                }

                list.add(map);
            }
        }
        return list;
    }
}
