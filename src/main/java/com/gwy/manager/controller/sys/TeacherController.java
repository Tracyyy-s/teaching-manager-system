package com.gwy.manager.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Teacher;
import com.gwy.manager.entity.TeacherAssess;
import com.gwy.manager.service.impl.TeacherAssessServiceImpl;
import com.gwy.manager.service.impl.TeacherCourseServiceImpl;
import com.gwy.manager.service.impl.TeacherServiceImpl;
import com.gwy.manager.service.impl.TermTargetServiceImpl;
import com.gwy.manager.util.DateUtilCustom;
import org.springframework.beans.factory.annotation.Autowired;
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
    private TeacherServiceImpl teacherService;

    @Autowired
    private TeacherCourseServiceImpl teacherCourseService;

    @Autowired
    private TermTargetServiceImpl termTargetService;

    @Autowired
    private TeacherAssessServiceImpl teacherAssessService;

    @PostMapping("/getTeacherInfo")
    public String getTeacherInfo(@RequestBody Map<String, String> map) {

        String teacherNo = map.get("teacherNo");
        return JSONObject.toJSONStringWithDateFormat(teacherService.getTeacherInfo(teacherNo), DateUtilCustom.DATE_PATTERN);
    }

    /**
     * 教师修改密码
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/updatePassword")
    public ResultVO updatePassword(@RequestBody Map<String, String> map) {

        String teacherNo = map.get("teacherNo");
        String password = map.get("password");
        return teacherService.updatePassword(teacherNo, password);
    }

    /**
     * 修改教师信息
     * @param teacher 传递教师
     * @return  返回结果
     */
    @PostMapping("/updateInfo")
    public ResultVO updateInfo(@RequestBody Teacher teacher) {

        return teacherService.updateTeacher(teacher);
    }

    /**
     * 获得教师本学期的课程
     * @param map   结果体
     * @return  返回集
     */
    @PostMapping("/getTermCourses")
    public ResultVO getTermCourses(@RequestBody Map<String, String> map) {

        String teacherNo = map.get("teacherNo");
        return teacherCourseService.getCoursesByTeacherAndTerm(teacherNo);
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
     * 教师提交评价指标
     * @param teacherAssess 教师评价
     * @return  结果集
     */
    @PostMapping("/postAssess")
    public ResultVO postAssess(@RequestBody TeacherAssess teacherAssess) {
        return teacherAssessService.addTeacherAssess(teacherAssess);
    }
}
