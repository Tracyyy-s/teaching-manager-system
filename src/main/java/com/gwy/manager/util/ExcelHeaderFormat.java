package com.gwy.manager.util;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.enums.UserOption;
import com.gwy.manager.entity.Dept;
import com.gwy.manager.dto.ExcelSheetPO;
import com.gwy.manager.entity.Teacher;
import com.gwy.manager.mapper.DeptMapper;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author Tracy
 * @date 2020/11/6 20:36
 */
@Component
public class ExcelHeaderFormat {

    public static final String TeacherExcel = "teacher";

    public static final String TargetExcel = "target";

    public static final String InvalidHeaders = "Invalid Headers";

    private static final String[] TeacherHeaders = new String[] {
            "教师号","姓名","性别","密码","生日","学历","毕业院校",
            "政治面貌", "入职年份","总学时", "入职学院","职称","职称获取时间"
    };

    /**
     * 根据传递来的标题头和对象类型检测是否合法
     * @param headerType    标题头类型
     * @param headers   标题头
     * @return  检测结果
     */
    public static boolean judgeHeaders(String headerType, String[] headers) {

        if (headerType.equals(ExcelHeaderFormat.TeacherExcel)) {
            return Arrays.equals(headers, TeacherHeaders);
        }

        return false;
    }

    /**
     * 通过对象类型获得合法的标题头
     * @param headerType    标题头类型
     * @return  返回合法的标题头
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
        result.put("msg", "Fail");

        //如果批量导入教师
        if (headerType.equals(UserOption.TEACHER.getUserType())) {
            Teacher teacher = new Teacher();

            try {
                if (obj.get(0).toString().endsWith(".00")) {
                    result.put("data", "Incorrect TeacherNo");
                    return result;
                }

                //设置教师号
                objMap.put("teacherNo", obj.get(0));

                //设置教师名
                objMap.put("teacherName", obj.get(1));

                //设置教师性别
                if (obj.get(2).equals("男")) {
                    objMap.put("gender", 1);
                } else if (obj.get(2).equals("女")) {
                    objMap.put("gender", 0);
                } else {
                    result.put("data", "Incorrect Gender");
                    return result;
                }

                //设置教师密码
                objMap.put("password", obj.get(3));

                //设置教师生日
                objMap.put("birth", DateUtilCustom.string2Date((String) obj.get(4)));

                //设置教师学历
                objMap.put("degree", obj.get(5));

                //设置教师毕业院校
                objMap.put("graduated", obj.get(6));

                //设置教师政治面貌
                objMap.put("political", obj.get(7));

                //设置教师入职年份
                objMap.put("entryYear", Integer.parseInt((String) obj.get(8)));

                //设置教师总学时
                objMap.put("sumHour", Integer.parseInt((String) obj.get(9)));

                //设置教师入职学院
                objMap.put("deptId", obj.get(10));

                //设置教师职称
                objMap.put("professional", obj.get(11));

                //设置教师职称获得时间
                objMap.put("professionalTime", DateUtilCustom.string2Date((String) obj.get(12)));
            } catch (Exception e) {
                e.printStackTrace();
                result.put("data", "Data ParseException");
                return result;
            }

            try {
                BeanUtils.populate(teacher, objMap);
            } catch (IllegalAccessException | InvocationTargetException e) {
                result.put("data", "Incorrect Data Type to Package Into Bean");
                return result;
            }

            result.put("msg", "Success");
            result.put("data", teacher);
            return result;
        }

        return null;
    }

    @Autowired
    private DeptMapper deptMapper;

    /**
     * 通过传入文件导入Bean对象
     * @param deptId 学院id
     * @param headerType    头类型
     * @param file  传入文件
     * @param <T>   导入的pojo类型
     * @return  结果集
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

                resultVO.setData(titleMap);
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
                if (thisData != null && thisData.get("msg").equals("Fail")) {
                    resultVO.setData(thisData.get("data"));
                    return resultVO;
                } else if (thisData != null && thisData.get("msg").equals("Success")) {

                    //如果传递的是教师类型
                    if (headerType.equals(ExcelHeaderFormat.TeacherExcel)) {
                        Teacher teacher = (Teacher)thisData.get("data");

                        //如果院系不为管理员所在学院
                        if (!teacher.getDeptId().equals(dept.getDeptName())) {
                            resultVO.setData("仅可添加本学院的教师");
                            return resultVO;
                        } else {
                            teacher.setDeptId(dept.getDeptId());
                        }

                        //识别成功，添加
                        data.add((T)teacher);
                    }
                    //数据识别成功
                    //data.add((T) thisData.get("data"));
                }
            }

            //存储本页Excel的数据
            thisSheet.put("dataList", data);
            map.put(po.getSheetName(), thisSheet);
        }

        resultVO.success(map);
        return resultVO;
    }
}
