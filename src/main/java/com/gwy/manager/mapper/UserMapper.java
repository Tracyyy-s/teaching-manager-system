package com.gwy.manager.mapper;

import com.gwy.manager.domain.entity.User;
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
public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    User selectByPrimaryKey(String userId);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    List<User> selectUsersByDeptId(String deptId);

    List<String> selectUserNamesByIds(@Param("userIds") List<String> userIds);

    @MapKey("user_id")
    Map<String, Map<String, String>> selectUserNamesForMapByIds(@Param("userIds") List<String> userIds);

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