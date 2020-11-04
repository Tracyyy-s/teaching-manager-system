package com.gwy.manager.controller.teacher;

import com.gwy.manager.dto.Response;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Teacher;
import com.gwy.manager.entity.TeacherAssess;
import com.gwy.manager.service.impl.TeacherAssessServiceImpl;
import com.gwy.manager.service.impl.TeacherCourseServiceImpl;
import com.gwy.manager.service.impl.TeacherServiceImpl;
import com.gwy.manager.service.impl.TermTargetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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

    /**
     * 教师登录
     * @param teacherNo 教师号
     * @param password  密码
     * @return  返回结果
     */
    @PostMapping("/login")
    public ResultVO login(@RequestParam("teacherNo") String teacherNo,
                          @RequestParam("password") String password,
                          HttpSession session) {
        ResultVO resultVO = teacherService.login(teacherNo, password);

        if (resultVO.getResultCode().equals(Response.SUCCESS.getCode())) {
            session.setAttribute("teacher", teacherService.getTeacherByTno(teacherNo));
        }

        return resultVO;
    }

    /**
     * 教师登出
     * @param session 会话
     * @return  返回结果
     */
    @PostMapping("/logout")
    public ResultVO logout(HttpSession session) {
        ResultVO resultVO = new ResultVO();
        try {
            session.invalidate();
            resultVO.success("Success");
        } catch (Exception e) {
            resultVO.setData("Fail");
        }
        return resultVO;
    }

    /**
     * 教师修改密码
     * @param teacherNo 教师号
     * @param password  密码
     * @return  返回结果
     */
    @PostMapping("/updatePassword")
    public ResultVO updatePassword(@RequestParam("teacherNo") String teacherNo,
                                   @RequestParam("password") String password) {

        return teacherService.updatePassword(teacherNo, password);
    }

    /**
     * 修改教师信息
     * @param teacher 传递教师
     * @return  返回结果
     */
    @PostMapping("/updateInfo")
    public ResultVO updateInfo(Teacher teacher) {

        return teacherService.updateTeacher(teacher);
    }

    /**
     * 获得教师本学期的课程
     * @param teacherNo 教师号
     * @return  返回结果
     */
    @PostMapping("/getTermCourses")
    public ResultVO getTermCourses(@RequestParam("teacherNo") String teacherNo) {

        return teacherCourseService.getCoursesByTeacherAndTerm(teacherNo);
    }

    /**
     * 获得教师学期评价的指标
     * @return  结果集
     */
    @PostMapping("/getTermTargets")
    public ResultVO getTermTargets(@RequestParam("termId") String termId) {
        return termTargetService.getTeacherTermTargets(termId);
    }

    /**
     * 教师提交评价指标
     * @param teacherAssess 教师评价
     * @return  结果集
     */
    @PostMapping("/postAssess")
    public ResultVO postAssess(@RequestParam("teacherAssess") TeacherAssess teacherAssess) {
        return teacherAssessService.addTeacherAssess(teacherAssess);
    }
}
