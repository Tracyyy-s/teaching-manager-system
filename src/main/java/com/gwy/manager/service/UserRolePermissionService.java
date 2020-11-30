package com.gwy.manager.service;

import com.gwy.manager.dto.ResultVO;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/27 15:22
 */
public interface UserRolePermissionService {

    /**
     * 获得值得用户的权限列表
     * @param account   账号
     * @return  结果集
     */
    ResultVO getPermissionsOfUser(String account);
}
