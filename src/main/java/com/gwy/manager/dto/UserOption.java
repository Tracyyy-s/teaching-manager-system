package com.gwy.manager.dto;

/**
 * @author Tracy
 * @date 2020/11/5 14:05
 */
public enum UserOption {

    STUDENT("student", 1),
    TEACHER("teacher", 0);

    private String userType;
    private Integer targetType;

    UserOption() {

    }

    UserOption(String userType, Integer targetType) {
        this.userType = userType;
        this.targetType = targetType;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getTargetType() {
        return targetType;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }
}
