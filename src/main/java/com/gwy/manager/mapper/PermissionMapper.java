package com.gwy.manager.mapper;

import com.gwy.manager.domain.entity.Permission;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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

    @MapKey("permissionId")
    Map<Integer, Permission> selectAllForMap();
}