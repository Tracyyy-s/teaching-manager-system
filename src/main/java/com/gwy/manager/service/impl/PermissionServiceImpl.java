package com.gwy.manager.service.impl;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Permission;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.mapper.PermissionMapper;
import com.gwy.manager.mapper.RolePermissionMapper;
import com.gwy.manager.service.PermissionService;
import com.gwy.manager.util.ResultVOUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/30 8:59
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public ResultVO getPermissionsByRoleIds(List<Integer> roleIds) {

        ResultVO resultVO;

        List<Permission> permissions = permissionMapper.selectByRoleIds(roleIds);
        if (CollectionUtils.isEmpty(permissions)) {
            resultVO = ResultVOUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO = ResultVOUtil.success(permissions);
        }

        return resultVO;
    }

    @Override
    public ResultVO getPermissionsByRoleId(Integer roleId) {
        ResultVO resultVO;

        List<Permission> permissions = permissionMapper.selectByRoleId(roleId);
        if (CollectionUtils.isEmpty(permissions)) {
            resultVO = ResultVOUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO = ResultVOUtil.success(permissions);
        }

        return resultVO;
    }

    @Override
    public ResultVO getPermissionsByIds(List<Integer> permissionIds) {

        ResultVO resultVO;

        List<Permission> permissions = permissionMapper.selectByIds(permissionIds);
        if (CollectionUtils.isEmpty(permissions)) {
            resultVO = ResultVOUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO = ResultVOUtil.success(permissions);
        }

        return resultVO;
    }

    @Transactional
    @Override
    public ResultVO updateRolePermission(Integer roleId, List<Integer> permissionIds) {

        ResultVO resultVO;

        int i = rolePermissionMapper.deleteByRoleId(roleId);
        if (i == 0) {
            resultVO = ResultVOUtil.error(ResponseDataMsg.Fail.getMsg());
            return resultVO;
        }

        int j = rolePermissionMapper.insertBatch(roleId, permissionIds);
        if (j == 0) {
            resultVO = ResultVOUtil.error(ResponseDataMsg.Fail.getMsg());
        } else {
            resultVO = ResultVOUtil.success(ResponseDataMsg.Success.getMsg());
        }

        return resultVO;
    }
}
