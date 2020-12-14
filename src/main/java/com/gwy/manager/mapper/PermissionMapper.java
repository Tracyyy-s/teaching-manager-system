package com.gwy.manager.mapper;

import com.gwy.manager.entity.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@Component
public interface PermissionMapper {
    int deleteByPrimaryKey(Integer permissionId);

    int insert(Permission record);

    Permission selectByPrimaryKey(Integer permissionId);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    List<Permission> selectByIds(@Param("permissionIds") List<Integer> permissionIds);

    List<Permission> selectByUserId(String userId);

    List<Permission> selectByRoleIds(@Param("roleIds") List<Integer> roleIds);

    List<Permission> selectByRoleId(@Param("roleId") Integer roleId);

    Integer selectIdByName(String permissionName);
}