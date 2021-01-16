package com.gwy.manager.domain.entity;

import java.util.Date;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
public class SysLog {

    private Integer id;

    private String userId;

    private String authorities;

    private String requestUrl;

    private String ip;

    private String locale;

    private String resultMessage;

    private String data;

    private String dataExplain;

    private String type;

    private String typeExplain;

    private Date createTime;

    private String params;

    public SysLog() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl == null ? null : requestUrl.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale == null ? null : locale.trim();
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage == null ? null : resultMessage.trim();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    public String getDataExplain() {
        return dataExplain;
    }

    public void setDataExplain(String dataExplain) {
        this.dataExplain = dataExplain;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeExplain() {
        return typeExplain;
    }

    public void setTypeExplain(String typeExplain) {
        this.typeExplain = typeExplain;
    }

    @Override
    public String toString() {
        return "SysLog{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", authorities='" + authorities + '\'' +
                ", requestUrl='" + requestUrl + '\'' +
                ", ip='" + ip + '\'' +
                ", locale='" + locale + '\'' +
                ", resultMessage='" + resultMessage + '\'' +
                ", data='" + data + '\'' +
                ", dataExplain='" + dataExplain + '\'' +
                ", type='" + type + '\'' +
                ", typeExplain='" + typeExplain + '\'' +
                ", createTime=" + createTime +
                ", params='" + params + '\'' +
                '}';
    }
}