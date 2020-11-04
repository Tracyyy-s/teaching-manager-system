package com.gwy.manager.dto;

/**
 * @author Tracy
 * @date 2020/11/4 13:58
 */
public enum Response {

    SUCCESS(1, "SUCCESS"),
    FAIL(-1, "ERROR");

    private Integer code;
    private String message;

    Response() {
    }

    Response(Integer code, String message) {
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
