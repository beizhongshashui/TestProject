package com.yuyoubang.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xiaoyuan on 16/11/7.
 */
public class DateUtils {
    public static String getDateToString(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
        return sf.format(d);
    }

    public static String getDateToMonth(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("MM/dd");
        return sf.format(d);
    }

    public static int getDateToDays(long endTime,long startTime) {
        SimpleDateFormat sf = new SimpleDateFormat("MM/dd");
        return (int) ((endTime - startTime ) / (1000 * 60 * 60 * 24));
    }


}
