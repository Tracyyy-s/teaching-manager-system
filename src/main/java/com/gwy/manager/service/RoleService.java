package com.gwy.manager.service;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Role;

/**
 * @author Tracy
 * @date 2020/11/30 12:54
 */
public interface RoleService {

    /**
     * 获得所有角色
     * @return  结果集
     */
    ResultVO getAllRoles();

    /**
     * 添加角色
     * @return  结果集
     */
    ResultVO addRole(Role role);
}
