package com.gwy.manager.service;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Admin;

import java.util.List;

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

    /**
     * 获得所有的管理员
     * @return  结果集
     */
    ResultVO getAllAdmins();

    /**
     * 添加admin用户
     * @param admin 预添加
     * @return  结果集
     */
    ResultVO addAdmin(Admin admin);

    /**
     * 删除admin用户
     * @param adminNo   adminNo
     * @return  结果集
     */
    ResultVO deleteAdmin(String adminNo);

    /**
     * 获得管理员管理的所有学院的id
     * @return  结果集
     */
    ResultVO getDeptsById(String adminNo);

    /**
     * 修改管理员管理的学院id
     * @param adminNo   管理员id
     * @param list  管理学院列表
     * @return  结果集
     */
    ResultVO updateDeptIds(String adminNo, List<String> list);
}
