package com.gwy.manager.controller;

import com.alibaba.fastjson.JSONObject;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Role;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.enums.ResponseStatus;
import com.gwy.manager.service.impl.DeptServiceImpl;
import com.gwy.manager.service.impl.PermissionServiceImpl;
import com.gwy.manager.service.impl.TermServiceImpl;
import com.gwy.manager.service.impl.UserRoleServiceImpl;
import com.gwy.manager.util.DateUtilCustom;
import com.gwy.manager.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/5 13:57
 */
@CrossOrigin
@RestController
public class BaseController {

    @Autowired
    private TermServiceImpl termService;

    @Autowired
    private UserRoleServiceImpl userRoleService ;

    @Autowired
    private PermissionServiceImpl permissionService;

    @Autowired
    private DeptServiceImpl deptService;

    @GetMapping("/")
    public ResultVO root() {
        return ResultVOUtil.success("Welcome to Teaching Manager System.");
    }

    @GetMapping("/login")
    public ResultVO login() {
        return ResultVOUtil.error("Sorry, Not Found.");
    }

    /**
     * 获得所有的学期信息
     * @return 返回结果
     */
    @PostMapping("/getTerms")
    public String getTerms() {
        return JSONObject.toJSONStringWithDateFormat(termService.getTerms(), DateUtilCustom.DATE_PATTERN);
    }

    /**
     * 获得当前学期信息
     * @return  结果集
     */
    @PostMapping("/getCurrentTerm")
    public String getCurrentTerm() {
        return JSONObject.toJSONStringWithDateFormat(termService.getCurrentTerm(), DateUtilCustom.DATE_PATTERN);
    }

    /**
     * 获得用户所有权限
     * @param map   请求体
     * @return  结果集
     */
    @SuppressWarnings("unchecked")
    @PostMapping("/getUserPermissions")
    public ResultVO getUserPermissions(@RequestBody Map<String, String> map) {

        String account = map.get("account");
        ResultVO rolesOfUser = userRoleService.getUserRoles(account);

        if (rolesOfUser.getResultCode().equals(ResponseStatus.SUCCESS.getCode())) {

            List<Role> roles = (List<Role>) rolesOfUser.getData();

            List<Integer> roleIds = new ArrayList<>();
            for (Role role : roles) {
                roleIds.add(role.getRoleId());
            }

            return permissionService.getPermissionsByRoleIds(roleIds);
        }

        return rolesOfUser;
    }

    /**
     * 根据学院id获得学院
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("getDeptById")
    public ResultVO getDeptById(@RequestBody Map<String, String> map) {

        String deptId = map.get("deptId");
        return deptService.getDeptById(deptId);
    }
}
