package com.gwy.manager.dto;

import com.gwy.manager.enums.ResponseStatus;

/**
 * @author Tracy
 * @date 2020/11/4 13:14
 */
public class ResultVO {

    /**
     * 结果响应码
     */
    Integer resultCode;

    /**
     * 结果信息
     */
    String message;

    /**
     * 返回数据
     */
    Object data;

    public ResultVO() {
        this.setResultCode(ResponseStatus.FAIL.getCode());
        this.setMessage(ResponseStatus.FAIL.getMessage());
    }

    public ResultVO(Integer resultCode, String message, Object data) {
        this.resultCode = resultCode;
        this.message = message;
        this.data = data;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
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

    public void success(Object data) {
        this.setResultCode(ResponseStatus.SUCCESS.getCode());
        this.setMessage(ResponseStatus.SUCCESS.getMessage());
        this.setData(data);
    }

    @Override
    public String toString() {
        return "ResultVO{" +
                "resultCode=" + resultCode +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
