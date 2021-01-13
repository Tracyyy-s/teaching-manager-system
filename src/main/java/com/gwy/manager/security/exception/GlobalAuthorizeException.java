package com.gwy.manager.security.exception;

/**
 * @author Tracy
 * @date 2020/12/5 15:01
 */
public class GlobalAuthorizeException extends RuntimeException {

    /**
     * 自定义全局认证错误异常
     * @param message   认证错误信息
     */
    public GlobalAuthorizeException(String message) {
        super(message);
    }

}
