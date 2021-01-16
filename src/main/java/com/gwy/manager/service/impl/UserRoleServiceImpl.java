package com.gwy.manager.service.impl;

import com.gwy.manager.domain.constant.RoleName;
import com.gwy.manager.domain.dto.ResultVo;
import com.gwy.manager.domain.entity.Role;
import com.gwy.manager.domain.entity.User;
import com.gwy.manager.domain.enums.ResponseDataMsg;
import com.gwy.manager.mapper.RoleMapper;
import com.gwy.manager.mapper.UserMapper;
import com.gwy.manager.mapper.UserRoleMapper;
import com.gwy.manager.service.UserRoleService;
import com.gwy.manager.util.ResultVoUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Tracy
 * @date 2020/11/27 11:16
 */
@Service
@CacheConfig(cacheNames = "userRoles")
public class UserRoleServiceImpl implements UserRoleService {

    private static final String TOKEN_PREFIX = "eyJ*";

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private List<Role> getRolesOfUser(String account) {
        return roleMapper.selectByUserId(account);
    }

    @Cacheable(keyGenerator = "userRoles")
    @Override
    public ResultVo getUserRoles(String account) {

        ResultVo resultVO;

        List<Role> roles = this.getRolesOfUser(account);
        if (CollectionUtils.isEmpty(roles)) {
            resultVO = ResultVoUtil.error(ResponseDataMsg.NotFound);
        } else {
            resultVO = ResultVoUtil.success(roles);
        }

        return resultVO;
    }

    @Cacheable(keyGenerator = "userRoleIds")
    @Override
    public ResultVo getUserRoleIds(String account) {

        ResultVo resultVO;

        List<Role> roles = this.getRolesOfUser(account);
        if (CollectionUtils.isEmpty(roles)) {
            resultVO = ResultVoUtil.error(ResponseDataMsg.NotFound);
        } else {
            List<Integer> roleIds = new ArrayList<>();
            for (Role role : roles) {
                roleIds.add(role.getRoleId());
            }
            resultVO = ResultVoUtil.success(roleIds);
        }

        return resultVO;
    }

    @CacheEvict(allEntries = true, beforeInvocation = true)
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public ResultVo updateUserRole(String userId, List<Integer> roles) {

        ResultVo resultVO;

        Integer teacherRoleId = roleMapper.selectRoleIdByName(RoleName.TEACHER);

        //存储修改后用户的roleIds
        List<Integer> roleIds = new ArrayList<>();
        roleIds.add(teacherRoleId);
        for (Integer role : roles) {
            if (!role.equals(teacherRoleId)) {
                roleIds.add(role);
            }
        }

        //查询管理员角色id
        Integer adminRoleId = roleMapper.selectRoleIdByName(RoleName.ADMIN);

        try {
            //如果修改后没有管理员权限，则删除可管理学院列表
            if (!roleIds.contains(adminRoleId)) {
                userMapper.updateAvailableDeptIds(userId, "");
            } else {
                //如果有管理员权限
                //先判断用户之前是否为管理员
                User user = userMapper.selectByPrimaryKey(userId);

                //如果用户可管理学院列表为空
                if (StringUtils.isEmpty(user.getAvailableDeptIds())) {

                    //添加本学院为可管理学院
                    user.setAvailableDeptIds(user.getDeptId() + ",");
                    userMapper.updateByPrimaryKey(user);
                }
            }

            //删除用户原有角色
            userRoleMapper.deleteRoleOfUser(userId);

            //为用户添加新角色
            int i = userRoleMapper.addRolesForUser(userId, roleIds);
            if (i == 0) {
                resultVO = ResultVoUtil.error(ResponseDataMsg.Fail.getMsg());
            } else {
                resultVO = ResultVoUtil.success(ResponseDataMsg.Success.getMsg());
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultVoUtil.error(ResponseDataMsg.Fail.getMsg());
        }

        //删除所有缓存的token
        Set<String> keys = redisTemplate.keys(TOKEN_PREFIX);
        if (keys != null) {
            redisTemplate.delete(keys);
        }

        return resultVO;
    }
}
