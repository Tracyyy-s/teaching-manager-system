package com.gwy.manager.service;

import com.gwy.manager.domain.dto.ResultVo;

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
    ResultVo getUserRoles(String account);

    /**
     * 返回用户的角色id列表
     * @param account   用户账号
     * @return  结果集
     */
    ResultVo getUserRoleIds(String account);

    /**
     * 修改用户的角色
     * @param userId 用户id
     * @param roles  添加的角色集
     * @return  结果集
     */
    ResultVo updateUserRole(String userId, List<Integer> roles);
}
