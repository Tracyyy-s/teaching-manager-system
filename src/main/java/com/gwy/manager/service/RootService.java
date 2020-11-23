package com.gwy.manager.service;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Admin;

/**
 * @author Tracy
 * @date 2020/11/10 16:45
 */
public interface RootService {

    /**
     * root用户登录
     * @param account   账号
     * @param password  密码
     * @return  结果集
     */
    //ResultVO login(String account, String password);

    /**
     * root修改密码
     * @param password  密码
     * @return  结果集
     */
    ResultVO updatePassword(String password);

}
