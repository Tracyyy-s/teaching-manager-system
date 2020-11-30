package com.gwy.manager.controller.sys.user;

import com.alibaba.fastjson.JSONObject;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.service.impl.*;
import com.gwy.manager.util.DateUtilCustom;
import com.gwy.manager.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    private DeptServiceImpl deptService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRoleServiceImpl userRoleService;

    @Autowired
    private UserRolePermissionServiceImpl userRolePermissionService;

    @Autowired
    private PermissionServiceImpl permissionService;

    /**
     * root用户修改密码
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/changePassword")
    public ResultVO changePassword(@RequestBody Map<String, String> map) {

        String password = map.get("password");
        String newPassword = map.get("newPassword");
        return userService.updateRootPassword(password, newPassword);
    }

    /**
     * root用户获得所有管理员
     * @return 结果集
     */
    @PostMapping("/getAllAdmins")
    public String getAllAdmins() {
        return JSONObject.toJSONStringWithDateFormat(userService.getAllAdmin(), DateUtilCustom.DATE_PATTERN);
    }

    /**
     * 获得所有用户
     * @return  结果集
     */
    @PostMapping("/getAllUsers")
    public String getAllUsers() {
        return JSONObject.toJSONStringWithDateFormat(userService.getAllUsers(), DateUtilCustom.DATE_PATTERN);
    }

    /**
     * 获得某用户的所有角色
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/getUserRoles")
    public ResultVO getUserRole(@RequestBody Map<String, String> map) {

        String userId = map.get("userId");
        return userRoleService.getUserRoles(userId);
    }

    /**
     * 通过角色id获得权限
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/getPermissionsByRoleId")
    public ResultVO getPermissionsByRoleId(@RequestBody Map<String, String> map) {

        Integer roleId = null;
        try {
            roleId = Integer.parseInt(map.get("roleId"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return permissionService.getPermissionsByRoleId(roleId);
    }

    /**
     * 修改用户的角色
     * @param map   请求体
     * @return  结果集
     */
    @SuppressWarnings("unchecked")
    @PostMapping("/updateUserRole")
    public ResultVO updateUserRole(@RequestBody Map<String, Object> map) {

        List<Integer> roleIds = new ArrayList<>();
        String userId = (String) map.get("userId");
        try {
            List<String> rolesStr = (List<String>)map.get("data");
            for (String roleStr : rolesStr) {
                roleIds.add(Integer.parseInt(roleStr));
            }
        } catch (Exception e) {
            return ResultVOUtil.error(ResponseDataMsg.BadRequest.getMsg());
        }
        return userService.updateUserRole(userId, roleIds);
    }

    /**
     * 修改角色权限
     * @param map   请求体
     * @return  结果集
     */
    @SuppressWarnings("unchecked")
    @PostMapping("/updateRolePermission")
    public ResultVO updateRolePermission(@RequestBody Map<String, Object> map) {

        Integer roleId = Integer.parseInt((String)map.get("roleId"));
        List<String> permissionIdsStr = (List<String>)map.get("data");
        List<Integer> permissionIds = new ArrayList<>();
        try {
            for (String permissionIdStr : permissionIdsStr) {
                permissionIds.add(Integer.parseInt(permissionIdStr));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return permissionService.updateRolePermission(roleId, permissionIds);
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
     * 更新管理员可管理的学院
     * @param map   请求体
     * @return  结果集
     */
    @SuppressWarnings("unchecked")
    @PostMapping("/updateAvailableDeptIds")
    public ResultVO updateAvailableDeptIds(@RequestBody Map<String, Object> map) {

        List<String> list;
        try {
            list = (List<String>)map.get("deptIdList");
        } catch (Exception e) {
            ResultVO resultVO = new ResultVO();
            resultVO.setData(ResponseDataMsg.BadRequest.getMsg());

            return resultVO;
        }

        String userId = (String) map.get("userId");
        return userService.updateAvailableDeptIds(userId, list);
    }
}
