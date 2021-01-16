package com.gwy.manager.mapper;

import com.gwy.manager.domain.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/10 15:40
 */
@Component
public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    @Transactional(rollbackFor = {Exception.class})
    int insert(Role record);

    Role selectByPrimaryKey(Integer roleId);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    String selectRoleNameById(Integer roleId);

    Integer selectRoleIdByName(String roleName);

    List<Integer> selectRoleIdsByNames(@Param("roleNames") List<String> roleNames);

    List<Role> selectByUserId(String userId);
}