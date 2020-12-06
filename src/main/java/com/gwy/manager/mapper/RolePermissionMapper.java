package com.gwy.manager.mapper;

import com.gwy.manager.entity.RolePermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@Component
public interface RolePermissionMapper {
    int deleteByPrimaryKey(@Param("roleId") Integer roleId,
                           @Param("permissionId") Integer permissionId);

    int insert(RolePermission record);

    List<RolePermission> selectAll();

    List<Integer> selectPermissionIdsByRoleIds(@Param("roleIds") List<Integer> roleIds);

    List<Integer> selectPermissionIdsByRoleId(Integer roleId);

    int deleteByRoleId(Integer roleId);

    int insertBatch(@Param("roleId")Integer roleId, @Param("permissionIds") List<Integer> permissionIds);
}