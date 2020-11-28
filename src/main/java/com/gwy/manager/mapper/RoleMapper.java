package com.gwy.manager.mapper;

import com.gwy.manager.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    Role selectByPrimaryKey(Integer roleId);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    String selectRoleNameById(Integer roleId);

    Integer selectRoleIdByName(String roleName);

    List<Integer> selectRoleIdsByNames(@Param("roleNames") List<String> roleNames);

    List<Role> selectByUserId(String userId);
}