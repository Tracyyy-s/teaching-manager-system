package com.gwy.manager.service.impl;

import com.gwy.manager.constant.RoleName;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Role;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.mapper.RoleMapper;
import com.gwy.manager.service.RoleService;
import com.gwy.manager.util.ResultVOUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author Tracy
 * @date 2020/11/30 12:54
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

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
}
