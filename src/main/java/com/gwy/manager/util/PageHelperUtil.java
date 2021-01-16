package com.gwy.manager.util;

import com.github.pagehelper.PageInfo;
import com.gwy.manager.domain.constant.PageHelperConst;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/30 17:06
 */
public class PageHelperUtil {

    /**
     * 处理分页信息
     * @param map   请求体
     */
    public static void pageMsg(Map<String, Object> map) {

        map.putIfAbsent(PageHelperConst.PAGE_NUM, 1);
        map.putIfAbsent(PageHelperConst.PAGE_SIZE, 10);
    }

    /**
     * 将分页信息转化为Map
     * @param info  PageInfo
     * @param <T>   泛型
     * @return  结果集
     */
    public static <T> Map<String, Object> pageInfoToMap(PageInfo<T> info) {

        Map<String, Object> map = new LinkedHashMap<>();

        map.put(PageHelperConst.PAGE_NUM, info.getPageNum());
        map.put(PageHelperConst.PAGE_SIZE, info.getPageSize());
        map.put(PageHelperConst.PAGES, info.getPages());
        map.put(PageHelperConst.NEXT_PAGE, info.getNextPage());
        map.put(PageHelperConst.PRE_PAGE, info.getPrePage());
        map.put(PageHelperConst.HAS_NEXT_PAGE, info.isHasNextPage());
        map.put(PageHelperConst.HAS_PRE_PAGE, info.isHasPreviousPage());
        map.put(PageHelperConst.TOTAL, info.getTotal());

        //若传入已经是map类型
        if (info.getList().get(0) instanceof Map) {
            map.put(PageHelperConst.LIST, info.getList());
        } else {
            map.put(PageHelperConst.LIST, BeanUtil.beansToList(info.getList()));
        }

        return map;
    }
}
