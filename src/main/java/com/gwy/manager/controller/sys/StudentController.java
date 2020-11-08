package com.gwy.manager.controller.sys;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.StudentAssess;
import com.gwy.manager.service.impl.*;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     *
     * @param studentNo 学号
     * @param password  密码
     * @return 返回结果
     */
    @PostMapping("/updatePassword")
    public ResultVO updatePassword(@RequestParam("studentNo") String studentNo,
                                   @RequestParam("password") String password) {

        return studentService.updatePassword(studentNo, password);
    }

    /**
     * 获得学生该学期的课程
     *
     * @param studentNo 学号
     * @param termId    学期号
     * @return 返回结果
     */
    @PostMapping("/getTermCourses")
    public ResultVO getTermCourses(@RequestParam("studentNo") String studentNo,
                                   @RequestParam("termId") String termId) {

        return teacherCourseService
                .getCoursesByStudentAndTerm(studentNo, termId);
    }

    /**
     * 获得学生学期的评价指标
     *
     * @param termId 学期号
     * @return 返回结果
     */
    @PostMapping("/getTermTargets")
    public ResultVO getStudentTermTargets(@Param("termId") String termId) {
        return termTargetService.getStudentTermTargets(termId);
    }

    /**
     * 学生发送课程评价请求
     *
     * @param studentAssess 学生评价
     * @return 返回结果
     */
    @PostMapping("/postAssess")
    public ResultVO postAssess(StudentAssess studentAssess) {
        return studentAssessService.updateAppraise(studentAssess);
    }
}
