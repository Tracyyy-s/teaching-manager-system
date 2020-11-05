package com.gwy.manager.controller.admin;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.dto.UserOption;
import com.gwy.manager.entity.Target;
import com.gwy.manager.mapper.AdminMapper;
import com.gwy.manager.service.impl.AdminServiceImpl;
import com.gwy.manager.service.impl.TargetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Tracy
 * @date 2020/11/3 16:43
 */
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private TargetServiceImpl targetService;

    /**
     * 管理员登录
     * @param adminNo   管理员账号
     * @param password  管理员密码
     * @return  返回结果集
     */
    @PostMapping("/login")
    public ResultVO login(@RequestParam("adminNo") String adminNo,
                          @RequestParam("password") String password) {

        return adminService.login(adminNo, password);
    }

    /**
     * 管理员修改密码
     * @param adminNo   管理员账号
     * @param password  管理员密码
     * @return  返回结果集
     */
    @PostMapping("/updatePassword")
    public ResultVO updatePassword(@RequestParam("adminNo") String adminNo,
                                   @RequestParam("password") String password) {

        return adminService.updatePassword(adminNo, password);
    }

    /**
     * 获得评价指标
     * @return  返回结果集
     */
    @PostMapping("/getTargets")
    public ResultVO getTargets(@RequestParam("userType") String userType) {
        return targetService.getTargets(userType);
    }

    /**
     * 添加评价评价指标
     * @param target    预添加指标
     * @return  结果集
     */
    @PostMapping("/addTarget")
    public ResultVO addStudentTarget(@RequestParam("target") Target target,
                                     @RequestParam("targetType") Integer targetType) {
        return targetService.addTarget(target, targetType);
    }

    /**
     * 修改评价指标信息
     * @param target    预修改
     * @return  返回结果集
     */
    @PostMapping("updateTarget")
    public ResultVO updateTarget(Target target) {
        return targetService.updateTarget(target);
    }
}
