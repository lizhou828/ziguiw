package com.arj.ziguiw.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: liz
 * Date: 13-1-28
 * Time: P.M 4:48
 */
public class DateUtils {

    public static long getPreMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime().getTime();
    }

    public static long getTime(String value, String format) throws ParseException {
        return new SimpleDateFormat(format).parse(value).getTime();
    }

    public static String getStartTime(String startTime) {
        if(startTime != null && !startTime.equals("")){
            return startTime;
        }
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.MONTH,-1);
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }

    public static String getEndTime(String endTime) {
        if(endTime != null && !endTime.equals("")){
            return endTime;
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

}
