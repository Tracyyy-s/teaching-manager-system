package com.gwy.manager.enums;

/**
 * @author Tracy
 * @date 2020/11/4 13:58
 */
public enum ResponseStatus {

    /**
     * 定义请求响应码和响应集
     */
    SUCCESS(1, "SUCCESS"),
    FAIL(-1, "ERROR");

    private Integer code;
    private String message;

    ResponseStatus() {
    }

    ResponseStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
