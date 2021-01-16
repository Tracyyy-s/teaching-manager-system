package com.gwy.manager.service;

import com.gwy.manager.domain.dto.ResultVo;
import com.gwy.manager.domain.entity.Role;

/**
 * @author Tracy
 * @date 2020/11/30 12:54
 */
public interface RoleService {

    /**
     * 获得所有角色
     * @return  结果集
     */
    ResultVo getAllRoles();

    /**
     * 添加角色
     * @return  结果集
     */
    ResultVo addRole(Role role);

    /**
     * 删除相关角色
     * @param roleId    角色id
     * @return  结果集
     */
    ResultVo deleteRole(Integer roleId);
}
