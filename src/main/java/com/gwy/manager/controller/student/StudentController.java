package com.gwy.manager.controller.student;

import com.gwy.manager.dto.Response;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.StudentAssess;
import com.gwy.manager.service.impl.*;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
     * 学生登录
     *
     * @param studentNo 学号
     * @param password  密码
     * @param session   会话
     * @return resultVO
     */
    @PostMapping("/login")
    public ResultVO login(@RequestParam("studentNo") String studentNo,
                          @RequestParam("password") String password,
                          HttpSession session) {

        ResultVO resultVO = studentService.login(studentNo, password);

        //如果查询成功
        if (resultVO.getResultCode().equals(Response.SUCCESS.getCode())) {
            session.setAttribute("student", studentService.getStudent(studentNo));
        }

        return resultVO;
    }

    /**
     * 学生退出登录
     * @param session   会话
     * @return  返回结果
     */
    @PostMapping("/logout")
    public ResultVO logout(HttpSession session) {
        ResultVO resultVO = new ResultVO();

        try {
            session.invalidate();
            resultVO.success("Success!");
        } catch (Exception e) {
            resultVO.setData("Fail");
            logger.info("error {}", e.getMessage());
        }

        return resultVO;
    }

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
     * 获得所有的学期信息
     * @return 返回结果
     */
    @PostMapping("/getTerms")
    public ResultVO getTerms() {
        return termService.getTerms();
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
