package com.gwy.manager.security;

import com.gwy.manager.entity.*;
import com.gwy.manager.mapper.*;
import com.gwy.manager.redis.RedisUtil;
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
    private RedisUtil redisUtil;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        //权限集合
        List<GrantedAuthority> roles = new ArrayList<>();
        //用户密码
        String password;

        Object object = studentMapper.selectByPrimaryKey(account);
        if (object == null) {
            object = userMapper.selectByPrimaryKey(account);

            if (object == null) {
                throw new UsernameNotFoundException(account + " Not Found");
            }
        }

        if (object instanceof Student) {
            Student student = (Student) object;

            password = student.getPassword();
            Role studentRole = roleMapper.selectByUserId(student.getStudentNo()).get(0);
            roles = AuthorityUtils.commaSeparatedStringToAuthorityList(
                            "ROLE_" + studentRole.getRoleName());

        } else {
            com.gwy.manager.entity.User user = ((com.gwy.manager.entity.User) object);

            password = user.getPassword();
            List<Role> roleList = roleMapper.selectByUserId(user.getUserId());

            //遍历用户的角色
            for (Role role : roleList) {
                roles.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = roles.size() - 1; i >= 0; i--) {
            sb.append(roles.get(i).toString().split("_")[1]);
            if (i != 0) {
                sb.append("|");
            }
        }

        //获得该用户在redis中的key
        String keyInRedis = redisUtil.getUserKeyInRedis(sb.toString(), account);
        //将用户添加进redis
        if (!redisUtil.hasKey(keyInRedis)) {
            redisUtil.set(keyInRedis, object);

            //设置用户在redis中的过期时间
            redisUtil.expire(keyInRedis, 60 * 60 * 24);
        }

        return new User(account, password, roles);
    }
}
