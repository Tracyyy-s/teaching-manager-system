package com.gwy.manager.service;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Role;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/27 11:14
 */
public interface UserRoleService {

    /**
     * 返回用户的角色列表
     * @param account   用户账号
     * @return  结果集
     */
    ResultVO getUserRoles(String account);

    /**
     * 返回用户的角色id列表
     * @param account   用户账号
     * @return  结果集
     */
    ResultVO getUserRoleIds(String account);

    /**
     * 修改用户的角色
     * @param userId 用户id
     * @param roles  添加的角色集
     * @return  结果集
     */
    ResultVO updateUserRole(String userId, List<Integer> roles);
}
