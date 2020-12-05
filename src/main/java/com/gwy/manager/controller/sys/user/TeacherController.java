package com.gwy.manager.controller.sys.user;

import com.alibaba.fastjson.JSONObject;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.User;
import com.gwy.manager.service.impl.*;
import com.gwy.manager.util.DateUtilCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/2 19:25
 */
@CrossOrigin
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private TeacherCourseServiceImpl teacherCourseService;

    /**
     * 获得用户基本信息
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/getUserInfo")
    @PreAuthorize("hasAnyRole('ROLE_teacher', 'ROLE_root')")
    public String getTeacherInfo(@RequestBody Map<String, String> map) {

        String userId = map.get("userId");
        return JSONObject.toJSONStringWithDateFormat(userService.getUserInfo(userId), DateUtilCustom.DATE_PATTERN);
    }

    /**
     * 教师用户发送验证码请求
     * @param map   请求体
     * @return  返回集
     */
    @PostMapping("/sendCode")
    @PreAuthorize("hasAnyRole('ROLE_teacher', 'ROLE_root')")
    public ResultVO sendCode(@RequestBody Map<String, String> map) {

        String userId = map.get("userId");
        return userService.sendCode(userId);
    }

    /**
     * 用户修改密码
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/updatePassword")
    @PreAuthorize("hasAnyRole('ROLE_teacher', 'ROLE_root')")
    public ResultVO updatePassword(@RequestBody Map<String, String> map) {

        String userId = map.get("userId");
        String password = map.get("password");
        String vrCode = map.get("vrCode");
        return userService.updatePassword(userId, password, vrCode);
    }

    /**
     * 修改用户信息
     * @param user 传递用户
     * @return  返回结果
     */
    @PostMapping("/updateInfo")
    @PreAuthorize("hasAnyRole('ROLE_teacher', 'ROLE_root')")
    public ResultVO updateInfo(@RequestBody User user) {

        return userService.updateUser(user);
    }

    /**
     * 获得用户本学期的课程
     * @param map   结果体
     * @return  返回集
     */
    @PostMapping("/getTermCourses")
    @PreAuthorize("hasAuthority('timetableImporting')")
    public ResultVO getTermCourses(@RequestBody Map<String, String> map) {

        String userId = map.get("userId");
        return teacherCourseService.getCoursesByTeacherAndTerm(userId);
    }
}
