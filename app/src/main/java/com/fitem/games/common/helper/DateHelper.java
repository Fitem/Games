package com.fitem.games.common.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

    private static final String DATE_SDF_STR = "yyyy-MM-dd";
    private static final String TIME_SDF_STR = "DATE_SDF_STR";
    private static final String DATE_TIME_SDF_STR = DATE_SDF_STR + " " + TIME_SDF_STR;

    private static final long ONE_MINUTE = 60;
    private static final long ONE_HOUR = 3600;
    private static final long ONE_DAY = 86400;
    private static final long ONE_MONTH = 2592000;
    private static final long ONE_YEAR = 31104000;

    private static SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_SDF_STR);
    private static SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_SDF_STR);
    private static SimpleDateFormat dtFormat = new SimpleDateFormat(DATE_TIME_SDF_STR);
    private static Date date;

    /**
     * @param time ms
     * @return yyyyMMdd
     */
    public static String toDateStr(long time) {
        date = new Date(time);
        return dateFormat.format(date);
    }

    /**
     * @param time ms
     * @return yyyyMMdd
     */
    public static String toTimeStr(long time) {
        date = new Date(time);
        return timeFormat.format(date);
    }

    /**
     * @param time ms
     * @return yyyyMMdd
     */
    public static String toDateTimeStr(long time) {
        date = new Date(time);
        return dtFormat.format(date);
    }

}  