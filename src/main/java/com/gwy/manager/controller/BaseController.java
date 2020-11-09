package com.gwy.manager.controller;

import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.enums.ResponseStatus;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.service.impl.StudentServiceImpl;
import com.gwy.manager.service.impl.TeacherServiceImpl;
import com.gwy.manager.service.impl.TermServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author Tracy
 * @date 2020/11/5 13:57
 */
@CrossOrigin
@RestController
public class BaseController {

    private Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private TermServiceImpl termService;

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private TeacherServiceImpl teacherService;

    /**
     * 用户登录系统
     * @param account   账号
     * @param password  密码
     * @param session   会话
     * @return  结果集
     */
    @PostMapping("/login")
    public ResultVO login(@RequestParam("account") String account,
                          @RequestParam("password") String password,
                          HttpSession session) {
        ResultVO resultVO = studentService.login(account, password);

        //如果学生登录成功
        if (resultVO.getResultCode().equals(ResponseStatus.SUCCESS.getCode())) {
            session.setAttribute("student", resultVO.getData());
            return resultVO;
        } else if (resultVO.getData().equals(ResponseDataMsg.NotFound.getMsg())) {

            //学生登录失败，则教师进行登录
            resultVO = teacherService.login(account, password);

            //教师登录成功
            if (resultVO.getResultCode().equals(ResponseStatus.SUCCESS.getCode())) {
                session.setAttribute("teacher", resultVO.getData());
                resultVO.setData(ResponseDataMsg.Success.getMsg());
                return resultVO;
            }
        }

        return resultVO;
    }

    /**
     * 用户登出
     * @param session   会话
     * @return  结果集
     */
    @PostMapping("/logout")
    public ResultVO logout(HttpSession session) {

        ResultVO resultVO = new ResultVO();

        try {
            session.invalidate();
            resultVO.success(ResponseDataMsg.Success.getMsg());
        } catch (Exception e) {
            resultVO.setData(ResponseDataMsg.Fail.getMsg());
            logger.info("error {}", e.getMessage());
        }

        return resultVO;

    }

    /**
     * 获得所有的学期信息
     * @return 返回结果
     */
    @PostMapping("/getTerms")
    public ResultVO getTerms() {
        return termService.getTerms();
    }
}
