package com.gwy.manager.service;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Role;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/27 11:14
 */
public interface UserRoleService {

    /**
     * 返回用户的角色列表
     * @param account   用户账号
     * @return  结果集
     */
    ResultVO getUserRoles(String account);

    /**
     * 返回用户的角色id列表
     * @param account   用户账号
     * @return  结果集
     */
    ResultVO getUserRoleIds(String account);
}
