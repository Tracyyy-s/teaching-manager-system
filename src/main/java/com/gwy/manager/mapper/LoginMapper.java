package com.gwy.manager.mapper;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author Tracy
 * @date 2020/12/16 15:45
 */
@Component
public interface LoginMapper {

    /**
     * 自定义登录接口
     * @param account   用户账号
     * @return  用户加密密码
     */
    @Select("select password from all_user where account = #{account} ")
    String selectUserPasswordFromAll(String account);
}
