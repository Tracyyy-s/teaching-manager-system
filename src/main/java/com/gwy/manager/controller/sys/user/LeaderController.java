package com.gwy.manager.controller.sys.user;

import com.alibaba.fastjson.JSONObject;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.TeacherAssess;
import com.gwy.manager.service.impl.TeacherAssessServiceImpl;
import com.gwy.manager.service.impl.TermTargetServiceImpl;
import com.gwy.manager.service.impl.UserServiceImpl;
import com.gwy.manager.util.DateUtilCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/26 9:30
 */
@CrossOrigin
@RestController
@RequestMapping("/leader")
public class LeaderController {

    @Autowired
    private TeacherAssessServiceImpl teacherAssessService;

    @Autowired
    private TermTargetServiceImpl termTargetService;

    @Autowired
    private UserServiceImpl userService;

    /**
     * 获得学院内所有的教师
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/getTeachersInDept")
    public String getTeachersByDept(@RequestBody Map<String, String> map) {

        String deptId = map.get("deptId");
        return JSONObject.toJSONStringWithDateFormat(userService.getUsersOfDept(deptId), DateUtilCustom.DATE_PATTERN);
    }

    /**
     * 教师督导提交评价指标
     * @param teacherAssess 教师评价
     * @return  结果集
     */
    @PostMapping("/postAssess")
    @PreAuthorize("hasAuthority('teacherEvaluation')")
    public ResultVO postAssess(@RequestBody TeacherAssess teacherAssess) {
        return teacherAssessService.addTeacherAssess(teacherAssess);
    }

    /**
     * 获得教师学期评价的指标
     * @return  结果集
     */
    @PostMapping("/getTermTargets")
    public ResultVO getTermTargets(@RequestBody Map<String, String> map) {

        String termId = map.get("termId");
        return termTargetService.getTeacherTermTargets(termId);
    }

    /**
     * 获得教师的历史评价
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/getHistoryAssesses")
    public ResultVO getHistoryAssesses(@RequestBody Map<String,String> map) {

        String userId = map.get("userId");
        return teacherAssessService.getHistoryAssessesOfTeacher(userId);
    }

}
