package com.gwy.manager.domain.constant;

import com.gwy.manager.security.GlobalPasswordEncoder;

/**
 * @author Tracy
 * @date 2020/12/9 16:42
 */
public class ExcelConstants {

    public static final String TEACHER_EXCEL = "teacher";

    public static final String STUDENT_EXCEL = "student";

    public static final String TARGET_EXCEL = "target";

    public static final String INVALID_HEADERS = "Invalid Headers";

    public static final String EMAIL_REGEX = "^[0-9a-z]+\\w*@([0-9a-z]+\\.)+[0-9a-z]+$";

    public static final String[] TEACHER_HEADERS = new String[]{
            "教师号", "姓名", "性别", "密码", "邮箱", "生日", "学历", "毕业院校",
            "政治面貌", "入职年份", "总学时", "入职学院", "职称", "职称获取时间"
    };

    public static final String[] STUDENT_HEADERS = new String[]{
            "学号", "姓名", "性别", "密码", "邮箱", "生日", "班号",
            "政治面貌", "入学学院", "入学年份"
    };
}
