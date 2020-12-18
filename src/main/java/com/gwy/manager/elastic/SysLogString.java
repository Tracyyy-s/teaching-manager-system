package com.gwy.manager.elastic;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author Tracy
 * @date 2020/12/18 16:00
 */
@Document(indexName = "sys_log", indexStoreType = "log")
public class SysLogString {

    Integer id;

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
