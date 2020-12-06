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

    public static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_PATTERN = "yyyy-MM-dd";

    public static Date getDate() {
        return new Date();
    }

    public static Date getTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Date string2Date(String date) throws ParseException {
        return DateUtils.parseDate(date, DATE_PATTERN);
    }

    public static String date2String(Date date) {
        return DateFormatUtils.format(date, DATE_PATTERN);
    }

    public static String time2String(Date date) {
        return DateFormatUtils.format(date, TIME_PATTERN);
    }
}
