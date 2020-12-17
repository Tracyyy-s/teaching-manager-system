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
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Set;

/**
 * @author Tracy
 * @date 2020/11/30 8:59
 */
@CacheConfig(cacheNames = "permissions")
@Service
public class PermissionServiceImpl implements PermissionService {

    private static final String TOKEN_PREFIX = "eyJ*";

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Cacheable(keyGenerator = "byRoleIds")
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

    @Cacheable(key = "'all'")
    @Override
    public ResultVO getAllPermissions() {

        ResultVO resultVO;

        List<Permission> permissions = permissionMapper.selectAll();
        if (CollectionUtils.isEmpty(permissions)) {
            resultVO = ResultVOUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO = ResultVOUtil.success(permissions);
        }
        return resultVO;
    }

    @Cacheable(keyGenerator = "byRoleId")
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

    @Cacheable(keyGenerator = "byIds")
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

    @CacheEvict(allEntries = true, beforeInvocation = true)
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public ResultVO updateRolePermission(Integer roleId, List<Integer> permissionIds) {

        try {
            int i = rolePermissionMapper.deleteByRoleId(roleId);
            if (i == 0) {
                return ResultVOUtil.error(ResponseDataMsg.Fail.getMsg());
            }

            int j = rolePermissionMapper.insertBatch(roleId, permissionIds);
            if (j == 0) {
                return ResultVOUtil.error(ResponseDataMsg.Fail.getMsg());
            } else {
                return ResultVOUtil.success(ResponseDataMsg.Success.getMsg());
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        //删除所有的token信息
        Set<String> keys = redisTemplate.keys(TOKEN_PREFIX);
        if (keys != null) {
            redisTemplate.delete(keys);
        }

        return ResultVOUtil.error(ResponseDataMsg.Fail.getMsg());
    }
}
