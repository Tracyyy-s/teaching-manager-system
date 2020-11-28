package com.gwy.manager.mapper;

import com.gwy.manager.entity.UserRole;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper {
    int deleteByPrimaryKey(@Param("userId") String userId, @Param("roleId") Integer roleId);

    int insert(UserRole record);

    List<UserRole> selectAll();

    int deleteRoleOfUser(@Param("userId") String userId);

    int addRolesForUser(@Param("userId") String userId, @Param("roleIds") List<Integer> roleIds);
}