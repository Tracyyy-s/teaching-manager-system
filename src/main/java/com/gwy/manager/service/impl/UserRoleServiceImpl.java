package com.gwy.manager.service.impl;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Role;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.mapper.RoleMapper;
import com.gwy.manager.mapper.UserRoleMapper;
import com.gwy.manager.service.UserRoleService;
import com.gwy.manager.util.ResultVOUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/27 11:16
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private RoleMapper roleMapper;

    private List<Role> getRolesOfUser(String account) {
        return roleMapper.selectByUserId(account);
    }

    @Override
    public ResultVO getUserRoles(String account) {

        ResultVO resultVO;

        List<Role> roles = this.getRolesOfUser(account);
        if (CollectionUtils.isEmpty(roles)) {
            resultVO = ResultVOUtil.error(ResponseDataMsg.NotFound);
        } else {
            resultVO = ResultVOUtil.success(roles);
        }

        return resultVO;
    }

    @Override
    public ResultVO getUserRoleIds(String account) {

        ResultVO resultVO;

        List<Role> roles = this.getRolesOfUser(account);
        if (CollectionUtils.isEmpty(roles)) {
            resultVO = ResultVOUtil.error(ResponseDataMsg.NotFound);
        } else {
            List<Integer> roleIds = new ArrayList<>();
            for (Role role : roles) {
                roleIds.add(role.getRoleId());
            }
            resultVO = ResultVOUtil.success(roleIds);
        }

        return resultVO;
    }
}
