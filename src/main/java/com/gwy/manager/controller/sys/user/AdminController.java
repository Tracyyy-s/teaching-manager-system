package com.gwy.manager.controller.sys.user;

import com.alibaba.fastjson.JSONObject;
import com.gwy.manager.constant.ExcelConstants;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Student;
import com.gwy.manager.entity.Target;
import com.gwy.manager.service.impl.*;
import com.gwy.manager.util.DateUtilCustom;
import com.gwy.manager.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private TargetServiceImpl targetService;

    @Autowired
    private CourserServiceImpl courserService;

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private StudentAssessServiceImpl studentAssessService;

    @Autowired
    private TeacherCourseServiceImpl teacherCourseService;

    /**
     * 获得评价指标
     * @return  返回结果集
     */
    @PostMapping("/getTargets")
    @PreAuthorize("hasAnyAuthority('modifyStudentsEvaluationForm', 'modifyTeacherEvaluationForm')")
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
    @PreAuthorize("hasAnyAuthority('modifyStudentsEvaluationForm', 'modifyTeacherEvaluationForm')")
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
            return ResultVOUtil.error("NumberFormatException");
        }

        Target target = new Target();

        target.setTargetContent(targetContent);
        target.setWeight(intWeight);
        target.setTargetObject(intTargetType);

        return targetService.addTarget(target);
    }

    /**
     * 修改评价指标信息
     * @param target    预修改
     * @return  返回结果集
     */
    @PostMapping("/updateTarget")
    @PreAuthorize("hasAnyAuthority('modifyStudentsEvaluationForm', 'modifyTeacherEvaluationForm')")
    public ResultVO updateTarget(@RequestBody Target target) {
        return targetService.updateTarget(target);
    }

    /**
     * 删除指标
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/deleteTarget")
    @PreAuthorize("hasAnyAuthority('modifyStudentsEvaluationForm', 'modifyTeacherEvaluationForm')")
    public ResultVO deleteTarget(@RequestBody Map<String, String> map) {

        String targetId = map.get("targetId");
        int intTargetId;
        try {
            intTargetId = Integer.parseInt(targetId);
        } catch (NumberFormatException e) {
            return ResultVOUtil.error("NumberFormatException");
        }

        return targetService.deleteTarget(intTargetId);
    }

    /**
     * 获得学院内所有的教师
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/getTeachersByDept")
    @PreAuthorize("hasAuthority('teacherListImporting')")
    public String getTeachersByDept(@RequestBody Map<String, String> map) {
        String deptId = map.get("deptId");
        return JSONObject.toJSONStringWithDateFormat(userService.getUsersOfDept(deptId), DateUtilCustom.DATE_PATTERN);
    }

    /**
     * 根据教师号获得指定教师
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/getTeacherById")
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_root')")
    public String getTeacher(@RequestBody Map<String, String> map) {

        String adminNo = map.get("adminNo");
        String userId = map.get("userId");
        return JSONObject.toJSONStringWithDateFormat(userService.getUserById(adminNo, userId), DateUtilCustom.DATE_PATTERN);
    }

    /**
     * 在学院内模糊匹配教师姓名
     * @param map   结果集
     * @return  返回集
     */
    @PostMapping("/getTeachersMatchNameInDept")
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_root')")
    public String getTeachersMatchNameInDept(@RequestBody Map<String, String> map) {

        String adminNo = map.get("adminNo");
        String deptId = map.get("deptId");
        String name = map.get("name");
        return JSONObject.toJSONStringWithDateFormat(userService.getUserMatchNameInDept(adminNo, deptId, name), DateUtilCustom.DATE_PATTERN);
    }

    /**
     * 获得某学院的所有学生
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/getStudentsByDept")
    @PreAuthorize("hasAuthority('studentListImporting')")
    public String getStudentsByDept(@RequestBody Map<String, String> map) {

        String deptId = map.get("deptId");
        return JSONObject.toJSONStringWithDateFormat(studentService.getStudentsByDept(deptId), DateUtilCustom.DATE_PATTERN);
    }

    /**
     * 根据学号获得某学生
     * @param map   请求体
     * @return  返回值
     */
    @PostMapping("/getStudentById")
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_root')")
    public String getStudentByStudentNo(@RequestBody Map<String, String> map) {

        String adminNo = map.get("adminNo");
        String studentNo = map.get("studentNo");
        return JSONObject.toJSONStringWithDateFormat(studentService.getStudentInfoByAdmin(adminNo, studentNo), DateUtilCustom.DATE_PATTERN);
    }

    /**
     * 根据姓名匹配某学院的学生
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/getStudentsMatchNameInDept")
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_root')")
    public String getStudentsMatchName(@RequestBody Map<String, String> map) {

        String adminNo = map.get("adminNo");
        String deptId = map.get("deptId");
        String name = map.get("name");
        return JSONObject.toJSONStringWithDateFormat(studentService.getStudentsMatchName(adminNo, deptId, name), DateUtilCustom.DATE_PATTERN);
    }

    /**
     * 管理员修改学生信息
     * @param student   学生信息
     * @return  结果集
     */
    @PostMapping("/updateStudentInfo")
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_root')")
    public ResultVO updateStudentInfo(@RequestBody Student student) {

        return studentService.updateStudent(student);
    }

    /**
     * 获得某教师所教授的全部课程
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/getCoursesOfTeacher")
    @PreAuthorize("hasAuthority('teacherSchedule')")
    public ResultVO getCoursesOfTeacher(@RequestBody Map<String, String> map) {

        String userId = map.get("userId");
        return courserService.getCoursesOfTeacher(userId);
    }

    /**
     * 获得用户可管理的学院
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("getDeptsOfUser")
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_root')")
    public ResultVO getDeptsOfUsr(@RequestBody Map<String, String> map) {

        String userId = map.get("userId");
        return userService.getDeptIdsOfUser(userId);
    }

    /**
     * 获得某学院在某学期的所有课程
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/getCoursesOfDeptInTerm")
    public ResultVO getCoursesOfDeptInTerm(@RequestBody Map<String, String> map) {

        String deptId = map.get("deptId");
        String termId = map.get("termId");
        return teacherCourseService.getCoursesByDeptAndTerm(deptId, termId);
    }

    /**
     * 获得某门课程的学生评价
     * @param map   请求体
     * @return  结果集
     */
    @PostMapping("/getStudentAssessesByTcId")
    public ResultVO getStudentAssesses(@RequestBody Map<String, String> map) {

        String tcId = map.get("tcId");
        return studentAssessService.getStudentAssessesByTcId(tcId);
    }

    /**
     * 上传文件批量添加教师
     * @param deptId 学院id
     * @param file  文件
     * @return  结果集
     */
    @PostMapping("/importTeachers")
    @PreAuthorize("hasAuthority('teacherListImporting')")
    public String importTeachers(@RequestParam("deptId") String deptId,
                                 @RequestParam("file") MultipartFile file) {
        return JSONObject.toJSONStringWithDateFormat(userService.importUsersByFile(deptId, ExcelConstants.TEACHER_EXCEL, file), DateUtilCustom.DATE_PATTERN);
    }

    /**
     * 上传Excel文件批量添加学生
     * @param deptId 学院id
     * @param file  文件
     * @return  结果集
     */
    @PostMapping("/importStudents")
    @PreAuthorize("hasAuthority('studentListImporting')")
    public String importStudents(@RequestParam("deptId") String deptId,
                                 @RequestParam("file") MultipartFile file) {
        return JSONObject.toJSONStringWithDateFormat(studentService.importStudentsByFile(deptId, ExcelConstants.STUDENT_EXCEL, file), DateUtilCustom.DATE_PATTERN);
    }
}
