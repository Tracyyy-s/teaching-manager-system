package com.gwy.manager.security;

import com.gwy.manager.constant.RoleName;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.*;
import com.gwy.manager.mapper.*;
import com.gwy.manager.redis.RedisUtil;
import com.gwy.manager.service.impl.PermissionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    private StudentMapper studentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {

        //用户密码
        String password;

        Object object = studentMapper.selectByPrimaryKey(account);
        if (object == null) {
            object = userMapper.selectByPrimaryKey(account);

            if (object == null) {
                throw new UsernameNotFoundException(account + " Not Found");
            }
        }

        List<Role> roles = new ArrayList<>();
        List<Permission> permissions = new ArrayList<>();

        if (object instanceof Student) {
            Student student = (Student) object;

            password = student.getPassword();
            Role studentRole = roleMapper.selectByUserId(student.getStudentNo()).get(0);

            //添加学生角色
            roles.add(studentRole);
            //将学生角色权限添加至列表
            permissions.addAll(permissionMapper.selectByRoleId(studentRole.getRoleId()));
        } else {
            com.gwy.manager.entity.User user = ((com.gwy.manager.entity.User) object);

            password = user.getPassword();
            List<Role> roleList = roleMapper.selectByUserId(user.getUserId());

            List<Integer> roleIds = new ArrayList<>();
            //遍历用户的角色
            for (Role role : roleList) {
                roleIds.add(role.getRoleId());
            }

            //添加角色
            roles.addAll(roleList);
            //添加所有角色的权限
            permissions.addAll(permissionMapper.selectByRoleIds(roleIds));

        }

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
