package com.gwy.manager.util;

import com.google.common.collect.Maps;
import com.gwy.manager.domain.entity.User;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/16 19:39
 */
public class BeanUtil {

    private static final String PASSWORD = "password";

    /**
     * 将对象转换为map
     *
     * @param bean 对象
     * @return 返回值
     */
    public static <T> Map<String, Object> beanToMap(T bean) {

        Map<String, Object> map = new LinkedHashMap<>();

        if (bean != null) {
            fillBeanFields(bean, map);
        }

        return map;
    }

    /**
     * 将beans转为list
     *
     * @param beans 对象
     * @return 返回值
     */
    public static <T> Collection<Map<String, Object>> beansToList(Collection<T> beans) {

        ArrayList<Map<String, Object>> list = new ArrayList<>();
        if (beans != null) {
            for (T bean : beans) {
                Map<String, Object> map = Maps.newLinkedHashMap();

                fillBeanFields(bean, map);
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 将map转为javaBean
     * @param map   map
     * @param beanClass bean
     * @param <T>   泛型
     * @return  result
     * @throws Exception    exception
     */
    public static <T> T mapToObject(Map<String, Object> map,
                                    Class<T> beanClass) throws Exception {

        T t = beanClass.getConstructor().newInstance();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Field field = beanClass.getDeclaredField(entry.getKey());
            field.setAccessible(true);
            field.set(t, entry.getValue());
        }
        return  t;
    }

    /**
     * 将bean对象的属性填充至map中
     *
     * @param bean 对象
     * @param map  结果map
     * @param <T>  对象泛型
     */
    private static <T> void fillBeanFields(T bean, Map<String, Object> map) {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            if (!name.equals(PASSWORD)) {
                try {
                    map.put(name, field.get(bean));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
