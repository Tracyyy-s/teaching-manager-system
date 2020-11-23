package com.gwy.manager.security;

import com.gwy.manager.entity.Admin;
import com.gwy.manager.entity.Root;
import com.gwy.manager.entity.Student;
import com.gwy.manager.entity.Teacher;
import com.gwy.manager.mapper.*;
import com.gwy.manager.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
    private AdminMapper adminMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private RootMapper rootMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        //权限集合
        List<GrantedAuthority> role;
        //用户密码
        String password;

        Object object = studentMapper.selectByPrimaryKey(account);
        if (object == null) {
            object = teacherMapper.selectByPrimaryKey(account);

            if (object == null) {
                object = adminMapper.selectByPrimaryKey(account);

                if (object == null) {
                    object = rootMapper.selectByPrimaryKey(account);

                    if (object == null) {
                        throw new UsernameNotFoundException(account + " Not Found");
                    }
                }
            }
        }

        if (object instanceof Student) {
            Student student = (Student) object;

            password = student.getPassword();
            role = AuthorityUtils.commaSeparatedStringToAuthorityList(
                            "ROLE_" + roleMapper.selectRoleNameById(student.getRoleId()));

        } else if (object instanceof Teacher) {
            Teacher teacher = (Teacher) object;

            password = teacher.getPassword();
            role = AuthorityUtils
                    .commaSeparatedStringToAuthorityList(
                            "ROLE_" + roleMapper.selectRoleNameById(teacher.getRoleId()));
        } else if (object instanceof Admin) {
            Admin admin = (Admin) object;

            password = admin.getPassword();
            role = AuthorityUtils.commaSeparatedStringToAuthorityList(
                            "ROLE_" + roleMapper.selectRoleNameById(admin.getRoleId()));
        } else {
            Root root = (Root) object;

            password = root.getPassword();
            role = AuthorityUtils.commaSeparatedStringToAuthorityList(
                    "ROLE_" + roleMapper.selectRoleNameById(root.getRoleId()));
        }

        //获得该用户在redis中的key
        String keyInRedis = redisUtil.getUserKeyInRedis(role.get(0).toString(), account);

        //将用户添加进redis
        redisUtil.set(keyInRedis, object);

        //设置用户在redis中的过期时间
        redisUtil.expire(keyInRedis, 60 * 60 * 24);

        return new User(account, password, role);
    }
}
