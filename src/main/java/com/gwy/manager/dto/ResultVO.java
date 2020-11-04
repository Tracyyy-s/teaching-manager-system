package com.gwy.manager.dto;

/**
 * @author Tracy
 * @date 2020/11/4 13:14
 */
public class ResultVO {

    Integer resultCode;

    String message;

    Object data;

    public ResultVO() {
        this.setResultCode(Response.FAIL.getCode());
        this.setMessage(Response.FAIL.getMessage());
    }

    public ResultVO(Integer resultCode, String message, Object data) {
        this.resultCode = resultCode;
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public void success(Object data) {
        this.setResultCode(Response.SUCCESS.getCode());
        this.setMessage(Response.SUCCESS.getMessage());
        this.setData(data);
    }
}
