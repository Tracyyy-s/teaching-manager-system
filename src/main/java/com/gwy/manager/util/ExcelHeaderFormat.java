package com.gwy.manager.util;

import com.gwy.manager.dto.UserOption;
import com.gwy.manager.entity.Teacher;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.*;

/**
 * @author Tracy
 * @date 2020/11/6 20:36
 */
public class ExcelHeaderFormat {

    public static final String TeacherExcel = "teacher";

    public static final String TargetExcel = "target";

    public static final String InvalidHeaders = "Invalid Headers";

    private static final String[] TeacherHeaders = new String[] {
            "教师号","姓名","性别","密码","生日","学历","毕业院校",
            "政治面貌", "入职年份","入职学院","职称","职称获取时间"
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
                objMap.put("teacherNo", obj.get(0));
                objMap.put("teacherName", obj.get(1));
                objMap.put("gender", obj.get(2).equals("男") ? 1 : 0);
                objMap.put("password", obj.get(3));
                objMap.put("birth", DateUtilCustom.string2Date((String) obj.get(4)));
                objMap.put("degree", obj.get(5));
                objMap.put("graduated", obj.get(6));
                objMap.put("political", obj.get(7));
                objMap.put("entryYear", obj.get(8));
                objMap.put("deptId", obj.get(9));
                objMap.put("professional", obj.get(10));
                objMap.put("professionalTime", DateUtilCustom.string2Date((String) obj.get(11)));
            } catch (ParseException e) {
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
}
