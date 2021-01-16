package com.gwy.manager.util.file;

import com.gwy.manager.domain.constant.ExcelConstants;
import com.gwy.manager.domain.dto.ExcelSheetPo;
import com.gwy.manager.domain.dto.ResultVo;
import com.gwy.manager.domain.entity.Dept;
import com.gwy.manager.domain.entity.Student;
import com.gwy.manager.domain.entity.User;
import com.gwy.manager.domain.enums.ResponseDataMsg;
import com.gwy.manager.domain.enums.ResponseStatus;
import com.gwy.manager.domain.enums.UserOption;
import com.gwy.manager.mapper.DeptMapper;
import com.gwy.manager.security.GlobalPasswordEncoder;
import com.gwy.manager.util.DateUtilCustom;
import com.gwy.manager.util.ResultVoUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
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
public class ImportExcelFileUtil {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private static GlobalPasswordEncoder passwordEncoder;

    /**
     * 根据传递来的标题头和对象类型检测是否合法
     *
     * @param headerType 标题头类型
     * @param headers    标题头
     * @return 检测结果
     */
    public static boolean judgeHeaders(String headerType, String[] headers) {

        if (headerType.equals(ExcelConstants.TEACHER_EXCEL)) {
            return Arrays.equals(headers, ExcelConstants.TEACHER_HEADERS);
        } else if (headerType.equals(ExcelConstants.STUDENT_EXCEL)) {
            return Arrays.equals(headers, ExcelConstants.STUDENT_HEADERS);
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

        if (headerType.equals(ExcelConstants.TEACHER_EXCEL)) {
            return Arrays.toString(ExcelConstants.TEACHER_HEADERS);
        } else if (headerType.equals(ExcelConstants.STUDENT_EXCEL)) {
            return Arrays.toString(ExcelConstants.STUDENT_HEADERS);
        }

        return null;
    }

    /**
     * 将Excel中读取的数据解析为bean对象
     *
     * @param headerType 用户类型
     * @param obj        传入的list, 里面封装Excel中读取的信息
     * @return 结果集
     */
    public static Map<String, Object> dataToBean(String headerType, List<Object> obj) {

        //通过BeanUtils转化为pojo
        Map<String, Object> objMap = new LinkedHashMap<>();

        //返回结果
        Map<String, Object> result = new HashMap<>(5);
        result.put("msg", ResponseDataMsg.Fail.getMsg());

        //如果导入用户
        if (headerType.equals(UserOption.TEACHER.getUserType())) {

            return userDataToMap(obj, objMap, result);

        } else if (headerType.equals(UserOption.STUDENT.getUserType())) {

            return studentDataToMap(obj, objMap, result);
        }

        return null;
    }

    /**
     * 将用户信息转化为map
     *
     * @param obj    Excel一行
     * @param objMap 对象map
     * @param result 结果
     * @return 返回结果map
     */
    private static Map<String, Object> userDataToMap(List<Object> obj, Map<String, Object> objMap, Map<String, Object> result) {
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
    }

    /**
     * 将学生信息封装为map
     *
     * @param obj    Excel一整行
     * @param objMap 对象map
     * @param result 结果map
     * @return 结果集
     */
    private static Map<String, Object> studentDataToMap(List<Object> obj, Map<String, Object> objMap, Map<String, Object> result) {
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

    /**
     * 将map转化为Bean对象
     *
     * @param bean   bean对象
     * @param map    存储对象信息的map
     * @param result 返回结果
     * @param <T>    bean对象的类型
     * @return 结果map
     */
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

    /**
     * 判断公共字段是否合法
     *
     * @param obj    Excel数据集
     * @param objMap 对象map
     * @param result 结果map
     * @return 返回结果
     * @throws ParseException 数据转换异常
     */
    private static boolean isNotCorrectOnUserPublicFields(List<Object> obj, Map<String, Object> objMap, Map<String, Object> result) throws ParseException {
        //设置性别
        if ("男".equals(obj.get(2))) {
            objMap.put("gender", 1);
        } else if ("女".equals(obj.get(2))) {
            objMap.put("gender", 0);
        } else {
            result.put("data", "Incorrect Gender");
            return true;
        }

        //设置密码
        objMap.put("password", passwordEncoder.encode(obj.get(3).toString()));

        boolean matches = Pattern.matches(ExcelConstants.EMAIL_REGEX, obj.get(4).toString());

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
     * @return 结果集
     */
    public ResultVo importBeansByFile(String deptId, String headerType, MultipartFile file) {

        List<ExcelSheetPo> pos = new ArrayList<>();

        try {
            //将MultipartFile转化为File
            File tmpFile = MultipartFileUtil.multipartFileToFile(file);

            //使用Excel工具类加载Excel文件内容
            pos = ExcelUtil.readExcel(headerType, tmpFile);

            //删除临时文件
            MultipartFileUtil.deleteTempFile(tmpFile);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //存储每一页的数据
        List<Object> data;

        Map<String, Object> map = new LinkedHashMap<>();

        //导入数据者系别
        Dept dept;

        //遍历所有页
        for (ExcelSheetPo po : pos) {

            if (po.getTitle() != null && po.getTitle().equals(ExcelConstants.INVALID_HEADERS)) {

                Map<String, Object> titleMap = new LinkedHashMap<>();
                titleMap.put("Failure", "表格标题不匹配");
                titleMap.put("Expected", ImportExcelFileUtil.getValidHeaders(headerType));

                return ResultVoUtil.error(titleMap);
            }

            dept = deptMapper.selectByPrimaryKey(deptId);

            Map<String, Object> thisSheet = new LinkedHashMap<>();

            //设置本页的title和headers
            thisSheet.put("title", po.getTitle() == null ? "title" : po.getTitle());
            thisSheet.put("headers", po.getHeaders());

            List<List<Object>> dataList = po.getDataList();

            data = new ArrayList<>();

            //按行遍历本页中的所有数据
            for (List<Object> objects : dataList) {
                Map<String, Object> thisData = ImportExcelFileUtil.dataToBean(headerType, objects);

                //如果数据识别错误
                if (thisData != null && thisData.get("msg").equals(ResponseDataMsg.Fail.getMsg())) {
                    return ResultVoUtil.error(thisData.get("data"));
                } else if (thisData != null && thisData.get("msg").equals(ResponseDataMsg.Success.getMsg())) {

                    Object objBean = thisData.get("data");

                    //将bean对象加入本页面的list
                    ResultVo resultVO = this.sheetListAddBean(data, headerType, objBean, dept);

                    //如果添加错误
                    if (!resultVO.getResultCode().equals(ResponseStatus.SUCCESS.getCode())) {
                        return resultVO;
                    }
                }
            }

            //存储本页Excel的数据
            thisSheet.put("dataList", data);

            //页名和数据以K-V方式存储
            map.put(po.getSheetName(), thisSheet);
        }

        return ResultVoUtil.success(map);
    }

    /**
     * 将实例bean对象加入list
     *
     * @param list       对象列表
     * @param headerType 对象类型
     * @param object     对象
     * @param dept       系别
     * @return 结果集
     */
    private ResultVo sheetListAddBean(List<Object> list,
                                      String headerType,
                                      Object object,
                                      Dept dept) {

        //如果传递的是教师类型
        if (headerType.equals(ExcelConstants.TEACHER_EXCEL)) {
            User user = ((User) object);

            //如果院系不为管理员所在学院
            //****封装时将Excel中的学院名字段赋予deptId****
            if (!StringUtils.isEmpty(user.getDeptId()) && !user.getDeptId().equals(dept.getDeptName())) {
                return ResultVoUtil.error("添加的教师仅可属于管理员所在学院");
            } else {
                user.setDeptId(dept.getDeptId());
            }

            //识别成功，添加
            list.add(user);
        } else if (headerType.equals(ExcelConstants.STUDENT_EXCEL)) {
            //如果是学生类型
            Student student = ((Student) object);

            if (!StringUtils.isEmpty(student.getDeptId()) && !student.getDeptId().equals(dept.getDeptName())) {
                return ResultVoUtil.error("添加的学生仅可属于管理员所在学院");
            } else {
                student.setDeptId(dept.getDeptId());
            }

            list.add(student);
        }

        return ResultVoUtil.success(ResponseDataMsg.Success.getMsg());
    }
}
