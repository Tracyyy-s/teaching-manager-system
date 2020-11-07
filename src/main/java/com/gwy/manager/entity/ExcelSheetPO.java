package com.gwy.manager.entity;

import java.util.Arrays;
import java.util.List;

/**
 * 定义表格的数据对象
 * @author JIANGYOUYAO
 * @email 935090232@qq.com
 * @date 2017年12月20日
 */
public class ExcelSheetPO {

    /**
     * 表格标题
     */
    private String title;

    /**
     * sheet的名称
     */
    private String sheetName;

    /**
     * 头部标题集合
     */
    private String[] headers;

    /**
     * 数据集合
     */
    private List<List<Object>> dataList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public List<List<Object>> getDataList() {
        return dataList;
    }

    public void setDataList(List<List<Object>> dataList) {
        this.dataList = dataList;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    @Override
    public String toString() {
        return "ExcelSheetPO{" +
                "title='" + title + '\'' +
                ", sheetName='" + sheetName + '\'' +
                ", headers=" + Arrays.toString(headers) +
                ", dataList=" + dataList +
                '}';
    }
}
