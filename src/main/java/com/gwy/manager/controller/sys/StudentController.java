package com.gwy.manager.controller.sys;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.StudentAssess;
import com.gwy.manager.service.impl.*;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/2 19:24
 */
@CrossOrigin
@RestController
@RequestMapping("/student")
public class StudentController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private TeacherCourseServiceImpl teacherCourseService;

    @Autowired
    private TermTargetServiceImpl termTargetService;

    @Autowired
    private StudentAssessServiceImpl studentAssessService;

    @Autowired
    private TermServiceImpl termService;

    /**
     * 修改密码
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/updatePassword")
    public ResultVO updatePassword(@RequestBody Map<String, String> map) {

        String studentNo = map.get("studentNo");
        String password = map.get("password");
        String vrCode = map.get("vrCode");
        return studentService.updatePassword(studentNo, password, vrCode);
    }

    /**
     * 获得学生某学期的课程
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/getTermCourses")
    public ResultVO getTermCourses(@RequestBody Map<String, String> map) {

        String studentNo = map.get("studentNo");
        String termId = map.get("termId");
        return teacherCourseService
                .getCoursesByStudentAndTerm(studentNo, termId);
    }

    /**
     * 获得学生学期的评价指标
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/getTermTargets")
    public ResultVO getStudentTermTargets(@RequestBody Map<String, String> map) {

        String termId = map.get("termId");
        return termTargetService.getStudentTermTargets(termId);
    }

    /**
     * 学生发送课程评价请求
     *
     * @param studentAssess 学生评价
     * @return 返回结果
     */
    @PostMapping("/postAssess")
    public ResultVO postAssess(@RequestBody StudentAssess studentAssess) {
        return studentAssessService.updateAppraise(studentAssess);
    }

    /**
     * 学生发送验证码请求
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/sendCode")
    public ResultVO postCode(@RequestBody Map<String, String> map) {
        
        String studentNo = map.get("studentNo");
        return studentService.sendCode(studentNo);
    }
}
