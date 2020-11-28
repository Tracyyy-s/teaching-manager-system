package com.gwy.manager.service;

import com.gwy.manager.dto.ResultVO;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/27 15:22
 */
public interface UserRolePermissionService {

    /**
     * 获得值得用户的权限列表
     * @param account   账号
     * @return  结果集
     */
    ResultVO getPermissionsOfUser(String account);

    /**
     * 获得角色的权限
     * @param roleIds   角色列表
     * @return  结果集
     */
    ResultVO getPermissionIdsOfRoleIds(List<Integer> roleIds);

    /**
     * 获得指定角色的权限
     * @param roleId    角色id
     * @return  结果集
     */
    ResultVO getPermissionIdsOfRoleId(Integer roleId);

    /**
     * 根据权限id列表获得权限
     * @param permissionIds 权限id列表
     * @return  结果集
     */
    ResultVO getPermissionsByIds(List<Integer> permissionIds);
}
