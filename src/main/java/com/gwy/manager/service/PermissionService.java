package com.gwy.manager.service;

import com.gwy.manager.domain.dto.ResultVo;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/30 8:59
 */
public interface PermissionService {

    /**
     * 获得角色的权限
     * @param roleIds   角色列表
     * @return  结果集
     */
    ResultVo getPermissionsByRoleIds(List<Integer> roleIds);

    /**
     * 获得指定角色的权限
     * @param roleId    角色id
     * @return  结果集
     */
    ResultVo getPermissionsByRoleId(Integer roleId);

    /**
     * 获得所有权限
     * @return  结果集
     */
    ResultVo getAllPermissions();

    /**
     * 根据权限id列表获得权限
     * @param permissionIds 权限id列表
     * @return  结果集
     */
    ResultVo getPermissionsByIds(List<Integer> permissionIds);

    /**
     * 修改指定角色的对应权限
     * @param roleId    角色id
     * @param permissionIds 权限id
     * @return  结果集
     */
    ResultVo updateRolePermission(Integer roleId, List<Integer> permissionIds);
}
