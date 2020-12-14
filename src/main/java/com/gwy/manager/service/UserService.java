package com.gwy.manager.service;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Tracy
 * @date 2020/11/24 23:50
 */
public interface UserService {

    /**
     * 通过学院id和userId获得用户
     * @param adminNo   管理员用户id
     * @param userId 用户id
     * @return  查询结果
     */
    ResultVO getUserById(String adminNo, String userId);

    /**
     * 通过用户名进行模糊匹配
     * @param adminNo   管理员角色
     * @param deptId 学院id
     * @param name  匹配名字
     * @return  结果集
     */
    ResultVO getUserMatchNameInDept(String adminNo, String deptId, String name);

    /**
     * 修改用户信息
     * @param user 预修改的用户
     * @return  影响行数
     */
    ResultVO updateUser(User user);

    /**
     * 获得学院内所有用户
     * @param pageNum   页码
     * @param pageSize  页面大小
     * @param deptId 学院id
     * @return  结果集
     */
    ResultVO getUsersOfDept(int pageNum, int pageSize, String deptId);

    /**
     * 指定用户获得学院内所有用户
     * @param pageNum   页码
     * @param pageSize  页面大小
     * @param userId  用户id
     * @param deptId  学院id
     * @return  结果集
     */
    ResultVO getUsersOfDept(int pageNum, int pageSize, String userId, String deptId);

    /**
     * 传入Excel批量添加用户
     * @param deptId 学院id
     * @param headerType Excel的header类型
     * @param file  传入文件
     * @return  返回结果
     */
    ResultVO importUsersByFile(String deptId, String headerType, MultipartFile file);

    /**
     * 获得用户
     * @param userId 用户id
     * @return  结果集
     */
    ResultVO getUserInfo(String userId);

    /**
     * 发送验证码至Redis,验证码5min有效
     * @param userId 用户账号
     * @return  结果集
     */
    ResultVO sendCode(String userId);

    /**
     * 修改密码
     * @param userId   账号
     * @param password  密码
     * @param vrCode    验证码
     * @return  结果集
     */
    ResultVO updatePassword(String userId, String password, String vrCode);

    /**
     * 获得所有管理员
     * @return  获得结果集
     */
    ResultVO getAllAdmin(int pageNum, int pageSize);

    /**
     * 获得所有用户
     * @return  结果集
     */
    ResultVO getAllUsers(int pageNum, int pageSize);

    /**
     * 获得指定用户可管理的所有学院id
     * @param userId   用户id
     * @return  结果集
     */
    ResultVO getDeptIdsOfUser(String userId);

    /**
     * 修改管理员可管理的学院
     * @param userId    用户id
     * @param list  可管理学院id
     * @return  结果集
     */
    ResultVO updateAvailableDeptIds(String userId, List<String> list);
}
