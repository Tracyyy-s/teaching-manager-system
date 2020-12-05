package com.gwy.manager.util;

import com.github.pagehelper.PageInfo;
import com.gwy.manager.constant.PageHelperConst;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/30 17:06
 */
public class PageHelperUtil {

    public static void pageMsg(Map<String, Object> map) {

        map.putIfAbsent(PageHelperConst.pageNum, 1);
        map.putIfAbsent(PageHelperConst.pageSize, 10);
    }

    public static <T> Map<String, Object> pageInfoToMap(PageInfo<T> info) {

        Map<String, Object> map = new LinkedHashMap<>();

        map.put(PageHelperConst.pageNum, info.getPageNum());
        map.put(PageHelperConst.pageSize, info.getPageSize());
        map.put(PageHelperConst.pages, info.getPages());
        map.put(PageHelperConst.nextPage, info.getNextPage());
        map.put(PageHelperConst.prePage, info.getPrePage());
        map.put(PageHelperConst.hasNextPage, info.isHasNextPage());
        map.put(PageHelperConst.hasPrePage, info.isHasPreviousPage());
        map.put(PageHelperConst.total, info.getTotal());
        map.put(PageHelperConst.list, BeanUtil.beansToList(info.getList()));

        return map;
    }
}
