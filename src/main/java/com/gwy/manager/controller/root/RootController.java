package com.gwy.manager.controller.root;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Admin;
import com.gwy.manager.service.impl.RootServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/10 16:36
 */
@RestController
@CrossOrigin
@RequestMapping("/root")
public class RootController {

    @Autowired
    private RootServiceImpl rootService;

    /**
     * root用户登录
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/login")
    public ResultVO login(@RequestBody Map<String, String> map) {

        String account = map.get("account");
        String password = map.get("password");
        return rootService.login(account, password);
    }

    /**
     * root用户修改密码
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/changePassword")
    public ResultVO changePassword(@RequestBody Map<String, String> map) {

        String password = map.get("password");
        return rootService.updatePassword(password);
    }

    /**
     * root用户获得所有管理员
     * @return 结果集
     */
    @PostMapping("/getAllAdmins")
    public ResultVO getAllAdmins() {
        return rootService.getAllAdmins();
    }

    /**
     * root用户添加管理员
     * @param admin 预添加
     * @return  结果集
     */
    @PostMapping("/addAdmin")
    public ResultVO addAdmin(@RequestBody Admin admin) {
        return rootService.addAdmin(admin);
    }

    /**
     * root用户删除管理员
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/deleteAdmin")
    public ResultVO deleteAdmin(@RequestBody Map<String, String> map) {

        String adminNo = map.get("adminNo");
        return rootService.deleteAdmin(adminNo);
    }
}
