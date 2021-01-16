package com.gwy.manager.elastic;

/**
 * @author Tracy
 * @date 2020/12/18 16:00
 */
//@Document(indexName = "sys_log", indexStoreType = "log")
public class SysLogString {

    /**
     * 日志对应id
     */
    Integer id;

    /**
     * 将日志序列化后的JSONString
     */
    private String content;

    public SysLogString() {
    }

    public SysLogString(Integer id, String content) {
        this.id = id;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
