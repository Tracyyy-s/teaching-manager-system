package com.gwy.manager.controller.admin;

import com.gwy.manager.enums.ResponseStatus;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Target;
import com.gwy.manager.service.impl.AdminServiceImpl;
import com.gwy.manager.service.impl.CourserServiceImpl;
import com.gwy.manager.service.impl.TargetServiceImpl;
import com.gwy.manager.service.impl.TeacherServiceImpl;
import com.gwy.manager.util.ExcelHeaderFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/3 16:43
 */
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private TargetServiceImpl targetService;

    @Autowired
    private TeacherServiceImpl teacherService;

    @Autowired
    private CourserServiceImpl courserService;

    /**
     * 管理员登录
     * @param map   请求体
     * @param session   会话
     * @return  结果集
     */
    @PostMapping("/login")
    public ResultVO login(@RequestBody Map<String, String> map,
                          HttpSession session) {

        String adminNo = map.get("adminNo");
        String password = map.get("password");

        ResultVO resultVO = adminService.login(adminNo, password);

        if (resultVO.getResultCode().equals(ResponseStatus.SUCCESS.getCode())) {
            session.setAttribute("admin", resultVO.getData());
        }

        return resultVO;
    }

    /**
     * 管理员修改密码
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/updatePassword")
    public ResultVO updatePassword(@RequestBody Map<String, String> map) {

        String adminNo = map.get("adminNo");
        String password = map.get("password");
        return adminService.updatePassword(adminNo, password);
    }

    /**
     * 获得评价指标
     * @return  返回结果集
     */
    @PostMapping("/getTargets")
    public ResultVO getTargets(@RequestBody Map<String, String> map) {

        String userType = map.get("userType");
        return targetService.getTargets(userType);
    }

    /**
     * 添加评价指标
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/addTarget")
    public ResultVO addStudentTarget(@RequestBody Map<String, String> map) {

        String targetType = map.get("targetType");
        String weight = map.get("weight");
        String targetContent = map.get("targetContent");

        int intTargetType;
        int intWeight;
        try {
            intTargetType = Integer.parseInt(targetType);
            intWeight = Integer.parseInt(weight);
        } catch (NumberFormatException e) {

            ResultVO resultVO = new ResultVO();
            resultVO.setData("NumberFormatException");

            return resultVO;
        }

        Target target = new Target();

        target.setTargetContent(targetContent);
        target.setWeight(intWeight);
        target.setTargetObject(intTargetType);

        System.out.println(target);
        return targetService.addTarget(target);
    }

    /**
     * 修改评价指标信息
     * @param target    预修改
     * @return  返回结果集
     */
    @PostMapping("/updateTarget")
    public ResultVO updateTarget(@RequestBody Target target) {
        return targetService.updateTarget(target);
    }

    /**
     * 删除指标
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/deleteTarget")
    public ResultVO deleteTarget(@RequestBody Map<String, String> map) {

        String targetId = map.get("targetId");
        int intTargetId;
        try {
            intTargetId = Integer.parseInt(targetId);
        } catch (NumberFormatException e) {
            ResultVO resultVO = new ResultVO();
            resultVO.setData("NumberFormatException");
            return resultVO;
        }

        return targetService.deleteTarget(intTargetId);
    }

    /**
     * 获得学院内所有的教师
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/getTeachersByDept")
    public ResultVO getTeachersByDept(@RequestBody Map<String, String> map) {
        String deptId = map.get("deptId");
        return teacherService.getTeachersOfDept(deptId);
    }

    /**
     * 根据教师号获得指定教师
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/getTeacherByTeacherNo")
    public ResultVO getTeacher(@RequestBody Map<String, String> map) {

        String deptId = map.get("deptId");
        String teacherNo = map.get("teacherNo");
        return teacherService.getTeacherByTnoInDept(deptId, teacherNo);
    }

    /**
     * 获得某教师所教授的全部课程
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/getCoursesOfTeacher")
    public ResultVO getCoursesOfTeacher(@RequestBody Map<String, String> map) {

        String teacherNo = map.get("teacherNo");
        return courserService.getCoursesOfTeacher(teacherNo);
    }

    /**
     * 在学院内模糊匹配教师姓名
     * @param map   结果集
     * @return  返回集
     */
    @PostMapping("/getTeachersMatchNameInDept")
    public ResultVO getTeachersMatchNameInDept(@RequestBody Map<String, String> map) {

        String deptId = map.get("deptId");
        String name = map.get("name");
        return teacherService.getTeacherMatchNameInDept(deptId, name);
    }

    /**
     * 上传文件批量添加教师
     * @param deptId 学院id
     * @param file  文件
     * @return  结果集
     */
    @PostMapping("/importTeachers")
    public ResultVO importTeachers(@RequestParam("deptId") String deptId,
                                   @RequestParam("file") MultipartFile file) {
        return teacherService.importTeachersByFile(deptId, ExcelHeaderFormat.TeacherExcel, file);
    }

}
