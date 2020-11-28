package com.gwy.manager.mapper;

import com.gwy.manager.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    User selectByPrimaryKey(String userId);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    List<User> selectUsersByDeptId(String deptId);

    List<String> selectUserNamesByIds(@Param("userIds") List<String> userIds);

    int insertUsersByBatch(@Param("users") List<User> users);

    int updatePassword(@Param("userId")String userId,
                       @Param("password") String password);

    List<User> getUsersMatchNameInDept(@Param("deptId") String deptId,
                                       @Param("name") String name);

    User selectByUsername(String username);

    List<User> selectUsersByRoleName(String roleName);

    int updateAvailableDeptIds(@Param("userId") String userId,
                               @Param("deptIds") String deptIds);
}