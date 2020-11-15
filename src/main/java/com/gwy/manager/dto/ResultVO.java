package com.gwy.manager.dto;

import com.gwy.manager.enums.ResponseStatus;

/**
 * @author Tracy
 * @date 2020/11/4 13:14
 */
public class ResultVO {

    Integer resultCode;

    String resultMsg;

    Object data;

    public ResultVO() {
        this.setResultCode(ResponseStatus.FAIL.getCode());
        this.setResultMsg(ResponseStatus.FAIL.getMessage());
    }

    public ResultVO(Integer resultCode, String resultMsg, Object data) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.data = data;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void success(Object data) {
        this.setResultCode(ResponseStatus.SUCCESS.getCode());
        this.setResultMsg(ResponseStatus.SUCCESS.getMessage());
        this.setData(data);
    }
}
