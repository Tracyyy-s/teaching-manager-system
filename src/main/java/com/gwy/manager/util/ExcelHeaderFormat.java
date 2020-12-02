package com.gwy.manager.util;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Student;
import com.gwy.manager.entity.User;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.enums.UserOption;
import com.gwy.manager.entity.Dept;
import com.gwy.manager.dto.ExcelSheetPO;
import com.gwy.manager.mapper.DeptMapper;
import com.gwy.manager.security.GlobalPasswordEncoder;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author Tracy
 * @date 2020/11/6 20:36
 */
@Component
public class ExcelHeaderFormat {

    public static final String TeacherExcel = "teacher";

    public static final String StudentExcel = "student";

    public static final String TargetExcel = "target";

    public static final String InvalidHeaders = "Invalid Headers";

    private static final GlobalPasswordEncoder passwordEncoder = new GlobalPasswordEncoder();

    private static final String EmailRegex = "^[0-9a-z]+\\w*@([0-9a-z]+\\.)+[0-9a-z]+$";

    @Autowired
    private DeptMapper deptMapper;

    private static final String[] TeacherHeaders = new String[]{
            "教师号", "姓名", "性别", "密码", "邮箱", "生日", "学历", "毕业院校",
            "政治面貌", "入职年份", "总学时", "入职学院", "职称", "职称获取时间"
    };

    private static final String[] StudentHeaders = new String[]{
            "学号", "姓名", "性别", "密码", "邮箱", "生日", "班号",
            "政治面貌", "入学学院", "入学年份"
    };

    /**
     * 根据传递来的标题头和对象类型检测是否合法
     *
     * @param headerType 标题头类型
     * @param headers    标题头
     * @return 检测结果
     */
    public static boolean judgeHeaders(String headerType, String[] headers) {

        if (headerType.equals(ExcelHeaderFormat.TeacherExcel)) {
            return Arrays.equals(headers, TeacherHeaders);
        } else if (headerType.equals(ExcelHeaderFormat.StudentExcel)) {
            return Arrays.equals(headers, StudentHeaders);
        }

        return false;
    }

    /**
     * 通过对象类型获得合法的标题头
     *
     * @param headerType 标题头类型
     * @return 返回合法的标题头
     */
    public static String getValidHeaders(String headerType) {

        if (headerType.equals(ExcelHeaderFormat.TeacherExcel)) {
            return Arrays.toString(ExcelHeaderFormat.TeacherHeaders);
        }

        return null;
    }

    public static Map<String, Object> dataToBean(String headerType, List<Object> obj) {

        //通过BeanUtils转化为pojo
        Map<String, Object> objMap = new LinkedHashMap<>();

        //返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("msg", ResponseDataMsg.Fail.getMsg());

        //如果导入教师
        if (headerType.equals(UserOption.TEACHER.getUserType())) {
            User user = new User();

            try {
                if (obj.get(0).toString().endsWith(".00")) {
                    result.put("data", "Incorrect UserId");
                    return result;
                }

                //设置教师号
                objMap.put("userId", obj.get(0).toString());

                //设置教师名
                objMap.put("username", obj.get(1));

                //抽取公共字段并校验
                if (isNotCorrectOnUserPublicFields(obj, objMap, result)) {
                    return result;
                }

                //设置教师学历
                objMap.put("degree", obj.get(6));

                //设置教师毕业院校
                objMap.put("graduated", obj.get(7));

                //设置教师政治面貌
                objMap.put("political", obj.get(8));

                //设置教师入职年份
                objMap.put("entryYear", Integer.parseInt((String) obj.get(9)));

                //设置教师总学时
                objMap.put("sumHour", Integer.parseInt((String) obj.get(10)));

                //设置教师入职学院
                objMap.put("deptId", obj.get(11));

                //设置教师职称
                objMap.put("professional", obj.get(12));

                //设置教师职称获得时间
                objMap.put("professionalTime", DateUtilCustom.string2Date((String) obj.get(13)));
            } catch (Exception e) {
                e.printStackTrace();
                result.put("data", "Data ParseException");
                return result;
            }

            return mapToBean(user, objMap, result);

        } else if (headerType.equals(UserOption.STUDENT.getUserType())) {
            Student student = new Student();

            try {
                if (obj.get(0).toString().endsWith(".00")) {
                    result.put("data", "Incorrect studentId");
                    return result;
                }

                //设置学号
                objMap.put("studentNo", obj.get(0).toString());

                //设置学生姓名
                objMap.put("studentName", obj.get(1));

                //检查公共字段
                if (isNotCorrectOnUserPublicFields(obj, objMap, result)) {
                    return result;
                }

                //设置学生班级
                objMap.put("classId", obj.get(6));

                //设置学生政治面貌
                objMap.put("political", obj.get(7));

                //设置学生学院
                objMap.put("deptId", obj.get(8));

                //设置学生入学年份
                objMap.put("entryYear", Integer.parseInt((String) obj.get(9)));

            } catch (Exception e) {
                e.printStackTrace();
                result.put("data", "Data ParseException");
                return result;
            }

            return mapToBean(student, objMap, result);
        }

        return null;
    }

    private static <T> Map<String, Object> mapToBean(T bean, Map<String, Object> map, Map<String, Object> result) {

        try {
            BeanUtils.populate(bean, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            result.put("data", "Incorrect Data Type to Package Into Bean");
            return result;
        }

        result.put("msg", ResponseDataMsg.Success.getMsg());
        result.put("data", bean);
        return result;
    }

    private static boolean isNotCorrectOnUserPublicFields(List<Object> obj, Map<String, Object> objMap, Map<String, Object> result) throws ParseException {
        //设置性别
        if (obj.get(2).equals("男")) {
            objMap.put("gender", 1);
        } else if (obj.get(2).equals("女")) {
            objMap.put("gender", 0);
        } else {
            result.put("data", "Incorrect Gender");
            return true;
        }

        //设置密码
        objMap.put("password", passwordEncoder.encode(obj.get(3).toString()));

        boolean matches = Pattern.matches(EmailRegex, obj.get(4).toString());

        if (matches) {
            objMap.put("email", obj.get(4));
        } else {
            result.put("data", "Incorrect Email");
            return true;
        }

        //设置生日
        objMap.put("birth", DateUtilCustom.string2Date((String) obj.get(5)));

        return false;
    }

    /**
     * 通过传入文件导入Bean对象
     *
     * @param deptId     学院id
     * @param headerType 头类型
     * @param file       传入文件
     * @param <T>        导入的pojo类型
     * @return 结果集
     */
    @SuppressWarnings("unchecked")
    public <T> ResultVO importBeansByFile(String deptId, String headerType, MultipartFile file) {

        ResultVO resultVO = new ResultVO();
        List<ExcelSheetPO> pos = new ArrayList<>();

        try {
            //将MultipartFile转化为File
            File tmpFile = MultipartFileToFile.multipartFileToFile(file);
            pos = ExcelUtil.readExcel(headerType, tmpFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //存储每一页的数据
        List<T> data;

        Map<String, Object> map = new LinkedHashMap<>();

        Dept dept = deptMapper.selectByPrimaryKey(deptId);

        //遍历所有页
        for (ExcelSheetPO po : pos) {

            if (po.getTitle() != null && po.getTitle().equals(ExcelHeaderFormat.InvalidHeaders)) {

                Map<String, Object> titleMap = new LinkedHashMap<>();
                titleMap.put("Failure", "表格标题不匹配");
                titleMap.put("Expected", ExcelHeaderFormat.getValidHeaders(headerType));

                resultVO = ResultVOUtil.error(titleMap);
                return resultVO;
            }

            Map<String, Object> thisSheet = new LinkedHashMap<>();

            //设置本页的title和headers
            thisSheet.put("title", po.getTitle() == null ? "title" : po.getTitle());
            thisSheet.put("headers", po.getHeaders());

            List<List<Object>> dataList = po.getDataList();

            data = new ArrayList<>();

            //遍历本页中的所有数据
            for (List<Object> objects : dataList) {
                Map<String, Object> thisData = ExcelHeaderFormat.dataToBean(headerType, objects);

                //如果数据识别错误
                if (thisData != null && thisData.get("msg").equals(ResponseDataMsg.Fail.getMsg())) {
                    resultVO = ResultVOUtil.error(thisData.get("data"));
                    return resultVO;
                } else if (thisData != null && thisData.get("msg").equals(ResponseDataMsg.Success.getMsg())) {

                    //如果传递的是教师类型
                    if (headerType.equals(ExcelHeaderFormat.TeacherExcel)) {
                        User user = (User) thisData.get("data");

                        //如果院系不为管理员所在学院
                        //****封装时将Excel中的学院名字段赋予deptId****
                        if (!user.getDeptId().equals(dept.getDeptName())) {
                            resultVO = ResultVOUtil.error("添加的教师仅可属于管理员所在学院");
                            return resultVO;
                        } else {
                            user.setDeptId(dept.getDeptId());
                        }

                        //识别成功，添加
                        data.add((T) user);
                    } else if (headerType.equals(ExcelHeaderFormat.StudentExcel)) {
                        //如果是学生类型
                        Student student = (Student) thisData.get("data");

                        //如果院系不为管理员所在学院
                        //****封装时将Excel中的学院名字段赋予deptId****
                        if (!student.getDeptId().equals(dept.getDeptName())) {
                            resultVO = ResultVOUtil.error("添加的学生仅可属于管理员所在学院");
                            return resultVO;
                        } else {
                            student.setDeptId(dept.getDeptId());
                        }

                        //识别成功，添加
                        data.add((T) student);
                    }

                }
            }

            //存储本页Excel的数据
            thisSheet.put("dataList", data);
            map.put(po.getSheetName(), thisSheet);
        }

        resultVO = ResultVOUtil.success(map);
        return resultVO;
    }
}
