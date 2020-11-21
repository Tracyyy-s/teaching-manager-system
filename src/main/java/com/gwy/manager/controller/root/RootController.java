package com.gwy.manager.controller.root;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Admin;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.service.impl.AdminServiceImpl;
import com.gwy.manager.service.impl.DeptServiceImpl;
import com.gwy.manager.service.impl.RootServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @Autowired
    private DeptServiceImpl deptService;

    @Autowired
    private AdminServiceImpl adminService;

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
        return adminService.getAllAdmins();
    }

    /**
     * root用户添加管理员
     * @param admin 预添加
     * @return  结果集
     */
    @PostMapping("/addAdmin")
    public ResultVO addAdmin(@RequestBody Admin admin) {
        return adminService.addAdmin(admin);
    }

    /**
     * root用户删除管理员
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/deleteAdmin")
    public ResultVO deleteAdmin(@RequestBody Map<String, String> map) {

        String adminNo = map.get("adminNo");
        return adminService.deleteAdmin(adminNo);
    }

    /**
     * 获得所有学院信息
     * @return  结果集
     */
    @PostMapping("/getAllDepts")
    public ResultVO getAllDepts() {

        return deptService.getAllDepts();
    }

    /**
     * 获得指定管理员管理的所有学院
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/getAdminDepts")
    public ResultVO getAdminDepts(@RequestBody Map<String, String> map) {

        String adminNo = map.get("adminNo");
        return adminService.getDeptsById(adminNo);
    }

    /**
     * 更新管理员可管理的学院
     * @param map   请求体
     * @return  结果集
     */
    @SuppressWarnings("unchecked")
    @PostMapping("/updateAdminDeptIds")
    public ResultVO updateAdminsDeptsIds(@RequestBody Map<String, Object> map) {

        List<String> list;
        try {
            list = (List<String>)map.get("deptIdList");
        } catch (Exception e) {
            ResultVO resultVO = new ResultVO();
            resultVO.setData(ResponseDataMsg.BadRequest.getMsg());

            return resultVO;
        }

        String adminNo = (String) map.get("adminNo");
        return adminService.updateDeptIds(adminNo, list);
    }
}
