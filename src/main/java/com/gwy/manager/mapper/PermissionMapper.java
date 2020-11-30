package com.gwy.manager.mapper;

import com.gwy.manager.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
}