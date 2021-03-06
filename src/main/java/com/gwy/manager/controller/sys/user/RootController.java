package com.gwy.manager.controller.sys.user;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.gwy.manager.domain.constant.PageHelperConst;
import com.gwy.manager.domain.dto.ResultVo;
import com.gwy.manager.elastic.ElasticRepositoryHelper;
import com.gwy.manager.domain.entity.Role;
import com.gwy.manager.domain.enums.ResponseDataMsg;
import com.gwy.manager.domain.enums.SysLogType;
import com.gwy.manager.service.impl.*;
import com.gwy.manager.util.DateUtilCustom;
import com.gwy.manager.util.PageHelperUtil;
import com.gwy.manager.util.ResultVoUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

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
    private PermissionServiceImpl permissionService;

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private SysLogServiceImpl logService;

//    @Autowired
    private ElasticRepositoryHelper repositoryHelper;

    /**
     * root用户获得所有管理员
     *
     * @return 结果集
     */
    @PostMapping("/getAllAdmins")
    public String getAllAdmins(@RequestBody Map<String, Object> map) {

        PageHelperUtil.pageMsg(map);
        int pageNum = (int) map.get(PageHelperConst.PAGE_NUM);
        int pageSize = (int) map.get(PageHelperConst.PAGE_SIZE);
        return JSONObject.toJSONStringWithDateFormat(userService.getAllAdmin(pageNum, pageSize), DateUtilCustom.DATE_PATTERN);
    }

    /**
     * root用户获得所有用户
     *
     * @return 结果集
     */
    @PostMapping("/getAllUsers")
    public String getAllUsers(@RequestBody Map<String, Object> map) {

        PageHelperUtil.pageMsg(map);
        int pageNum = (int) map.get(PageHelperConst.PAGE_NUM);
        int pageSize = (int) map.get(PageHelperConst.PAGE_SIZE);
        return JSONObject.toJSONStringWithDateFormat(userService.getAllUsers(pageNum, pageSize), DateUtilCustom.DATE_PATTERN);
    }

    /**
     * root用户获得所有学生信息
     *
     * @param map 请求体
     * @return 结果集
     */
    @PostMapping("/getAllStudents")
    public String getAllStudents(@RequestBody Map<String, Object> map) {

        PageHelperUtil.pageMsg(map);
        int pageNum = (int) map.get(PageHelperConst.PAGE_NUM);
        int pageSize = (int) map.get(PageHelperConst.PAGE_SIZE);
        return JSONObject.toJSONStringWithDateFormat(studentService.getAllStudents(pageNum, pageSize), DateUtilCustom.DATE_PATTERN);
    }

    /**
     * root用户获得所有角色信息
     *
     * @return 结果集
     */
    @PostMapping("/getAllRoles")
    public ResultVo getAllRoles() {

        return roleService.getAllRoles();
    }

    /**
     * root用户获得所有权限信息
     *
     * @return 结果集
     */
    @PostMapping("/getAllPermissions")
    public ResultVo getAllPermissions() {
        return permissionService.getAllPermissions();
    }

    /**
     * 根据用户id获得用户
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/getUserById")
    public ResultVo getUserById(@RequestBody Map<String, String> map) {
        return userService.getUserById(map.get("userId"));
    }

    /**
     * root用户获得某用户的所有角色
     *
     * @param map 请求体
     * @return 结果集
     */
    @PostMapping("/getUserRoles")
    public ResultVo getUserRole(@RequestBody Map<String, String> map) {

        String userId = map.get("userId");
        return userRoleService.getUserRoles(userId);
    }

    /**
     * root用户通过角色id获得权限
     *
     * @param map 请求体
     * @return 结果集
     */
    @PostMapping("/getPermissionsByRoleId")
    public ResultVo getPermissionsByRoleId(@RequestBody Map<String, String> map) {

        int roleId;
        try {
            roleId = Integer.parseInt(map.get("roleId"));
        } catch (Exception e) {
            return ResultVoUtil.error(ResponseDataMsg.Fail.getMsg());
        }
        return permissionService.getPermissionsByRoleId(roleId);
    }

    /**
     * 添加新角色
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/addRole")
    public ResultVo addRole(@RequestBody Map<String, String> map) {

        String roleName = map.get("roleName");
        Role role = new Role();
        role.setRoleName(roleName);
        return roleService.addRole(role);
    }

    /**
     * 删除指定角色
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/deleteRole")
    public ResultVo deleteRole(@RequestBody Map<String, Integer> map) {

        Integer roleId = map.get("roleId");
        return roleService.deleteRole(roleId);
    }

    /**
     * root用户修改用户的角色
     *
     * @param map 请求体
     * @return 结果集
     */
    @SuppressWarnings("unchecked")
    @PostMapping("/updateUserRole")
    public ResultVo updateUserRole(@RequestBody Map<String, Object> map) {

        List<Integer> roleIds;
        String userId = (String) map.get("userId");
        try {
            roleIds = (List<Integer>) map.get("data");

        } catch (Exception e) {
            return ResultVoUtil.error(ResponseDataMsg.BadRequest.getMsg());
        }
        return userRoleService.updateUserRole(userId, roleIds);
    }

    /**
     * root用户修改角色权限
     *
     * @param map 请求体
     * @return 结果集
     */
    @SuppressWarnings("unchecked")
    @PostMapping("/updateRolePermission")
    public ResultVo updateRolePermission(@RequestBody Map<String, Object> map) {

        Integer roleId = Integer.parseInt((String) map.get("roleId"));
        List<Integer> permissionIds;
        try {
            permissionIds = (List<Integer>) map.get("data");
        } catch (NumberFormatException e) {
            return ResultVoUtil.error(ResponseDataMsg.BadRequest.getMsg());
        }
        return permissionService.updateRolePermission(roleId, permissionIds);
    }

    /**
     * root用户更新管理员可管理的学院
     *
     * @param map 请求体
     * @return 结果集
     */
    @SuppressWarnings("unchecked")
    @PostMapping("/updateAvailableDeptIds")
    public ResultVo updateAvailableDeptIds(@RequestBody Map<String, Object> map) {

        List<String> list;
        try {
            list = (List<String>) map.get("deptIdList");
        } catch (Exception e) {
            ResultVo resultVO;
            resultVO = ResultVoUtil.error(ResponseDataMsg.BadRequest.getMsg());

            return resultVO;
        }

        String userId = (String) map.get("userId");
        return userService.updateAvailableDeptIds(userId, list);
    }

    /**
     * root用户获得所有学院信息
     *
     * @return 结果集
     */
    @PostMapping("/getAllDepts")
    public ResultVo getAllDepts() {

        return deptService.getAllDepts();
    }

    /**
     * 获取所有日志类别
     *
     * @return 结果集
     */
    @PostMapping("/getLogTypes")
    public ResultVo getLogTypes() {

        SysLogType[] types = SysLogType.values();
        List<Map<String, String>> typeList = new ArrayList<>();
        for (SysLogType type : types) {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("value", type.getType());
            map.put("label", type.getTypeExplain());
            typeList.add(map);
        }
        return ResultVoUtil.success(typeList);
    }

    /**
     * 获得所有日志类型已经相应的数量
     *
     * @return 结果集
     */
    @PostMapping("/getLogTypeAndCount")
    public ResultVo getLogTypeAndCount() {
        return logService.getLogTypeAndCount();
    }

    /**
     * 通过日志类型获得所有日志
     *
     * @param map 请求体
     * @return 结果集
     */
    @PostMapping("/getLogInfoByType")
    public String getLogs(@RequestBody Map<String, Object> map) {

        PageHelperUtil.pageMsg(map);
        int pageNum = (int) map.get(PageHelperConst.PAGE_NUM);
        int pageSize = (int) map.get(PageHelperConst.PAGE_SIZE);
        String type = (String) map.get("type");
        return JSONObject.toJSONStringWithDateFormat(logService.getLogInfoByType(type, pageNum, pageSize), DateUtilCustom.TIME_PATTERN);
    }

    /**
     * 批量删除日志
     *
     * @param map 请求体
     * @return 结果集
     */
    @PostMapping("/deleteLogs")
    @SuppressWarnings("unchecked")
    public ResultVo deleteLogs(@RequestBody Map<String, Object> map) {

        List<Integer> deleteId;
        try {
            deleteId = (List<Integer>) map.get("deleteId");
        } catch (Exception e) {
            return ResultVoUtil.error("NumberFormatException");
        }

        return logService.deleteByBatch(deleteId);
    }

    /**
     * 获得日志的相关信息
     * @return  结果集
     */
    @PostMapping("/getLogInfo")
    public ResultVo getLogInfo() {
        return logService.getLogsInfo();
    }

    /**
     * 按条件导出日志
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/exportLogs")
    public String exportLogs(@RequestBody Map<String, String> map) {

        String strBeginTime = map.get("beginTime");
        String strEndTime = map.get("endTime");
        String type = map.get("type");

        Date beginTime, endTime;
        try {
            beginTime = DateUtilCustom.string2Time(strBeginTime);
            endTime = DateUtilCustom.string2Time(strEndTime);
        } catch (ParseException e) {
            return JSONObject.toJSONString(ResultVoUtil.error("ParseException"));
        }

        return JSONObject.toJSONStringWithDateFormat(logService.getLogByInterval(beginTime, endTime, type), DateUtilCustom.TIME_PATTERN);
    }

    /**
     * 根据关键字获得日志并高亮
     *
     * @param map 请求体
     * @return  结果集
     */
    /*@PostMapping("/getLogByKeyword")
    public ResultVo getLogByKeyword(@RequestBody Map<String, Object> map) throws IOException {

        PageHelperUtil.pageMsg(map);
        String keyword = (String) map.get("keyword");
        int pageNum = (int) map.get(PageHelperConst.PAGE_NUM);
        int pageSize = 50;

        List<Map<String, Object>> result = repositoryHelper.searchByKeyword(keyword, pageNum, pageSize);
        if (CollectionUtils.isEmpty(result)) {
            return ResultVoUtil.error(ResponseDataMsg.NotFound.getMsg());
        }

        return ResultVoUtil.success(PageHelperUtil.pageInfoToMap(new PageInfo<>(result)));
    }*/
}
