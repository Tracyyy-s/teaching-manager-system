package com.gwy.manager.service;

import com.gwy.manager.domain.dto.ResultVo;

/**
 * @author Tracy
 * @date 2021/1/15 23:31
 */
public interface VrCodeService {

    /**
     * 获得用户验证码的key
     *
     * @param userId 用户id
     * @return 结果集
     */
    String getCode(String userId);

    /**
     * 删除用户
     *
     * @param userId 用户id
     */
    void deleteCode(String userId);

    /**
     * 向指定用户发送验证码
     *
     * @param userId   用户id
     * @param userType 用户类型
     * @return 结果集
     */
    ResultVo sendCode(String userId, String userType);

    /**
     * 使用code更新密码
     *
     * @param userType 用户类型
     * @param userId   用户id
     * @param password 密码
     * @param vrCode   验证码
     * @return 结果集
     */
    ResultVo updatePasswordByCode(String userType,
                                  String userId,
                                  String password,
                                  String vrCode);
}
