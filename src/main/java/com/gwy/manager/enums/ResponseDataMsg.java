package com.gwy.manager.enums;

/**
 * @author Tracy
 * @date 2020/11/8 15:41
 */
public enum ResponseDataMsg {

    NotFound("Not Found"),
    PasswordIncorrect("Password Incorrect"),
    Success("Success"),
    Fail("Fail"),
    PermissionDeny("Permission Deny");

    ResponseDataMsg() {
    }

    ResponseDataMsg(String msg) {
        this.msg = msg;
    }

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
