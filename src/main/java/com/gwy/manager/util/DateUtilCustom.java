package com.gwy.manager.util;

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

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN);

    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat(TIME_PATTERN);

    public static Date getDate() {
        return new Date();
    }

    public static Date getTime() {
        return new Timestamp(new Date().getTime());
    }

    public static Date string2Date(String date) throws ParseException {
        return DATE_FORMAT.parse(date);
    }

    public static String date2String(Date date) {
        return DATE_FORMAT.format(date);
    }

    public static String time2String(Date date) {
        return TIME_FORMAT.format(date);
    }
}
