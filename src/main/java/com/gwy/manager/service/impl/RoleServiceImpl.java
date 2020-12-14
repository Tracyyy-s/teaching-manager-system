package com.gwy.manager.service.impl;

import com.gwy.manager.constant.RoleName;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Role;
import com.gwy.manager.entity.RolePermission;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.mapper.PermissionMapper;
import com.gwy.manager.mapper.RoleMapper;
import com.gwy.manager.mapper.RolePermissionMapper;
import com.gwy.manager.service.RoleService;
import com.gwy.manager.util.ResultVOUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author Tracy
 * @date 2020/11/30 12:54
 */
@CacheConfig(cacheNames = "roles")
@Service
public class RoleServiceImpl implements RoleService {

    private static final String DEFAULT_PERMISSION = "teacherCard";

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Cacheable(key = "'all'")
    @Override
    public ResultVO getAllRoles() {

        ResultVO resultVO;

        List<Role> roles = roleMapper.selectAll();
        if (CollectionUtils.isEmpty(roles)) {
            resultVO = ResultVOUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            roles.removeIf(role -> role.getRoleName().equals(RoleName.STUDENT) || role.getRoleName().equals(RoleName.ROOT));
            resultVO = ResultVOUtil.success(roles);
        }

        return resultVO;
    }

    @Transactional(rollbackFor = {Exception.class})
    @CacheEvict(allEntries = true, beforeInvocation = true)
    @Override
    public ResultVO addRole(Role role) {

        Integer id = roleMapper.selectRoleIdByName(role.getRoleName());
        if (id != null) {
            return ResultVOUtil.error("Already Exists");
        }

        try {
            int i = roleMapper.insert(role);
            if (i == 0) {
                return ResultVOUtil.error(ResponseDataMsg.Fail.getMsg());
            }

            RolePermission rolePermission = new RolePermission();
            Integer roleId = roleMapper.selectRoleIdByName(role.getRoleName());
            Integer permissionId = permissionMapper.selectIdByName(DEFAULT_PERMISSION);
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);

            //添加角色默认权限为资料卡片
            int j = rolePermissionMapper.insert(rolePermission);
            if (j == 0) {
                return ResultVOUtil.error(ResponseDataMsg.Fail.getMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return ResultVOUtil.success(ResponseDataMsg.Success.getMsg());
    }

    @Transactional(rollbackFor = {Exception.class})
    @CacheEvict(allEntries = true, beforeInvocation = true)
    @Override
    public ResultVO deleteRole(Integer roleId) {

        try {
            int i = roleMapper.deleteByPrimaryKey(roleId);
            int j = rolePermissionMapper.deleteByRoleId(roleId);

            if (i == 0 || j == 0) {
                return ResultVOUtil.error(ResponseDataMsg.Fail.getMsg());
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultVOUtil.error(ResponseDataMsg.Fail.getMsg());
        }

        return ResultVOUtil.success(ResponseDataMsg.Success.getMsg());
    }
}
