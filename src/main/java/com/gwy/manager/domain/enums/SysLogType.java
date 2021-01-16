package com.gwy.manager.domain.enums;

/**
 * @author Tracy
 * @date 2020/12/5 16:16
 */
public enum SysLogType {

    /**
     * 定义日志种类以及相应解释
     */
    All("All", "全部日志"),
    LoginLog("LoginLog", "登录日志"),
    SystemLog("SystemLog", "系统日志"),
    OperationLog("OperationLog", "操作日志");

    SysLogType(String type, String typeExplain) {
        this.type = type;
        this.typeExplain = typeExplain;
    }

    private String type;
    private String typeExplain;

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
}
