package com.gwy.manager.domain.enums;

/**
 * @author Tracy
 * @date 2020/11/8 15:41
 */
public enum ResponseDataMsg {

    /**
     * 定义返回结果集以及解释
     */
    NotFound("Not Found", "查找数据为空"),
    PasswordIncorrect("Password Incorrect"),
    Success("Success", "操作成功"),
    Fail("Fail", "操作失败"),
    PermissionDeny("Permission Deny", "权限不足"),
    BadRequest("Bad Request, Check Your Params", "参数错误"),
    UserNameORPasswordError("Username or Password Error", "用户名或密码错误"),
    NotLogin("Not Login! Login First!", "尚未登录");

    ResponseDataMsg() {
    }

    ResponseDataMsg(String msg) {
        this.msg = msg;
    }

    ResponseDataMsg(String msg, String explain) {
        this.msg = msg;
        this.explain = explain;
    }

    private String msg;

    private String explain;
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
