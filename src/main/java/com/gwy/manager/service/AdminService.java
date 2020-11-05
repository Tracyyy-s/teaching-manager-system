package com.gwy.manager.service;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Admin;

/**
 * @author Tracy
 * @date 2020/11/5 8:10
 */
public interface AdminService {

    /**
     * 管理员登录
     * @param adminNo   管理员账号
     * @param password  管理员密码
     * @return  返回结果集
     */
    ResultVO login(String adminNo, String password);

    /**
     * 修改密码
     * @param adminNo   管理员账号
     * @param password  管理员密码
     * @return  返回结果集
     */
    ResultVO updatePassword(String adminNo, String password);

    /**
     * 修改管理员信息
     * @param admin 预修改
     * @return  返回结果集
     */
    ResultVO updateAdmin(Admin admin);
}
