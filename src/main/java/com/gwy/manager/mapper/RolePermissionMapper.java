package com.gwy.manager.mapper;

import com.gwy.manager.entity.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePermissionMapper {
    int deleteByPrimaryKey(@Param("roleId") Integer roleId,
                           @Param("permissionId") Integer permissionId);

    int insert(RolePermission record);

    List<RolePermission> selectAll();

    List<Integer> selectPermissionIdsByRoleIds(@Param("roleIds") List<Integer> roleIds);

    List<Integer> selectPermissionIdsByRoleId(Integer roleId);
}