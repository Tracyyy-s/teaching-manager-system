package com.gwy.manager.controller.root;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Admin;
import com.gwy.manager.service.impl.RootServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * @param password  密码
     * @return  返回结果集
     */
    @PostMapping("/login")
    public ResultVO login(@RequestParam("password") String password) {
        return rootService.login(password);
    }

    /**
     * root用户修改密码
     * @param password  密码
     * @return  结果集
     */
    @PostMapping("/changePassword")
    public ResultVO changePassword(@RequestParam("password") String password) {
        return rootService.updatePassword(password);
    }

    /**
     * root用户获得所有管理员
     * @return
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
    public ResultVO addAdmin(Admin admin) {
        return rootService.addAdmin(admin);
    }

    /**
     * root用户删除管理员
     * @param adminNo   管理员账号
     * @return  结果集
     */
    @PostMapping("/deleteAdmin")
    public ResultVO deleteAdmin(@RequestParam("adminNo") String adminNo) {
        return rootService.deleteAdmin(adminNo);
    }
}
