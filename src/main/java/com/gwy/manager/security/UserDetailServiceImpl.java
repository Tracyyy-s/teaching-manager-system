package com.gwy.manager.security;

import com.gwy.manager.domain.constant.RoleName;
import com.gwy.manager.domain.entity.Permission;
import com.gwy.manager.domain.entity.Role;
import com.gwy.manager.mapper.LoginMapper;
import com.gwy.manager.mapper.PermissionMapper;
import com.gwy.manager.mapper.RoleMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/23 8:52
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {

        //用户密码
        String password = loginMapper.selectUserPasswordFromAll(account);

        //判断密码是否为空
        if (StringUtils.isEmpty(password)) {
            throw new UsernameNotFoundException(account + " Not Found");
        }

        List<Role> roleList = roleMapper.selectByUserId(account);

        List<Integer> roleIds = new ArrayList<>();
        //遍历用户的角色
        for (Role role : roleList) {
            roleIds.add(role.getRoleId());
        }

        //添加角色
        List<Role> roles = new ArrayList<>(roleList);
        //添加所有角色的权限
        List<Permission> permissions = new ArrayList<>(permissionMapper.selectByRoleIds(roleIds));

        StringBuilder sb = new StringBuilder();
        for (Role role : roles) {
            sb.append(RoleName.ROLE_PREFIX).append(role.getRoleName()).append(", ");
        }

        for (Permission permission : permissions) {
            sb.append(permission.getPermissionUrl()).append(", ");
        }

        return new User(account, password, AuthorityUtils.commaSeparatedStringToAuthorityList(sb.toString()));
    }
}
