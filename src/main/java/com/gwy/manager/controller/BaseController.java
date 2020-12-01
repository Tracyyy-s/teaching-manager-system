package com.gwy.manager.controller;

import com.alibaba.fastjson.JSONObject;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.service.impl.TermServiceImpl;
import com.gwy.manager.service.impl.UserRolePermissionServiceImpl;
import com.gwy.manager.util.DateUtilCustom;
import com.gwy.manager.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private UserRolePermissionServiceImpl userRolePermissionService;

    @GetMapping("/login")
    public ResultVO login() {
        return ResultVOUtil.error(ResponseDataMsg.NotLogin.getMsg());
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
    @PostMapping("/getUserPermissions")
    public ResultVO getUserPermissions(@RequestBody Map<String, String> map) {

        String account = map.get("account");
        return userRolePermissionService.getPermissionsOfUser(account);
    }
}
