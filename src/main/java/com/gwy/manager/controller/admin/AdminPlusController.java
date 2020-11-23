package com.gwy.manager.controller.admin;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/23 21:04
 */
@CrossOrigin
@RestController
@RequestMapping("/adminplus")
public class AdminPlusController {

    @Autowired
    private TeacherServiceImpl teacherService;

    /**
     * 修改教师的角色
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/updateTeacherRole")
    public ResultVO setTeacherLeader(@RequestBody Map<String, String> map) {

        String teacherNo = map.get("teacherNo");
        return teacherService.updateTeacherRole(teacherNo);
    }
}
