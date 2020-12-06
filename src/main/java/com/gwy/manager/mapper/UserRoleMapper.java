package com.gwy.manager.mapper;

import com.gwy.manager.entity.UserRole;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@Component
public interface UserRoleMapper {
    int deleteByPrimaryKey(@Param("userId") String userId, @Param("roleId") Integer roleId);

    int insert(UserRole record);

    int insertByBatch(@Param("userRoles") List<UserRole> userRoles);

    List<UserRole> selectAll();

    int deleteRoleOfUser(@Param("userId") String userId);

    int addRolesForUser(@Param("userId") String userId, @Param("roleIds") List<Integer> roleIds);
}