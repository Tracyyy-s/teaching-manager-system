package com.gwy.manager.service.impl;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Permission;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.mapper.PermissionMapper;
import com.gwy.manager.mapper.RolePermissionMapper;
import com.gwy.manager.service.UserRolePermissionService;
import com.gwy.manager.util.ResultVOUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/27 15:22
 */
@Service
public class UserRolePermissionServiceImpl implements UserRolePermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public ResultVO getPermissionsOfUser(String account) {

        ResultVO resultVO;

        List<Permission> permissions = permissionMapper.selectByUserId(account);
        if (CollectionUtils.isEmpty(permissions)) {
            resultVO = ResultVOUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO = ResultVOUtil.success(permissions);
        }

        return resultVO;
    }
}
