package com.gwy.manager.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Tracy
 * @date 2020/11/4 17:09
 */
public class DateUtilCustom {

    /**
     * 时间转换表达式
     */
    public static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期转换表达式
     */
    public static final String DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 获得当前日期
     * @return  当前日期
     */
    public static Date getDate() {
        return new Date();
    }

    /**
     * 获得当前时间
     * @return  当前时间
     */
    public static Date getTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 字符串转为日期
     * @param date  日期
     * @return  日期结果
     * @throws ParseException   转换异常
     */
    public static Date string2Date(String date) throws ParseException {
        return DateUtils.parseDate(date, DATE_PATTERN);
    }

    /**
     * 字符串转为时间
     * @param time  时间
     * @return  时间结果
     * @throws ParseException   转换异常
     */
    public static Date string2Time(String time) throws ParseException {
        return DateUtils.parseDate(time, TIME_PATTERN);
    }

    /**
     * 日期转为String
     * @param date  日期
     * @return  返回结果
     */
    public static String date2String(Date date) {
        return DateFormatUtils.format(date, DATE_PATTERN);
    }

    /**
     * 时间转为String
     * @param date  时间
     * @return  结果集
     */
    public static String time2String(Date date) {
        return DateFormatUtils.format(date, TIME_PATTERN);
    }
}
