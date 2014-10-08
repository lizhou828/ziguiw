package com.zigui.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用户子贵网解析数字化校园接口的时间字段
 * long类型时间格式化
 * @author zhouping
 *
 */
public class DateUtils {

//	/**
//	 * 将日期类型转化为long型
//	 * @param String
//	 *            sourceTime 待转化的时间
//	 * @param String
//	 *            dataFormat 日期的组织形式
//	 *
//	 * @return long 当前时间的长整型格式,如 1309574632778
//	 *
//	 */


    public  static long stringToLong(String sourceTime, String dataFormat) {
        long longTime;
        DateFormat f = new SimpleDateFormat(dataFormat);
        Date d;
        try {
            d = f.parse(sourceTime);
        } catch (ParseException e) {
            throw new IllegalStateException(e);
        }
        longTime = d.getTime();
        return longTime;

    }

    //	/**
//	 * 长整型转换为日期类型
//	 * @param long
//	 *            longTime 长整型时间
//	 * @param String
//	 *            dataFormat 时间格式
//	 * @return String 长整型对应的格式的时间
//	 *
//	 */
    public static String longToString(long longTime, String dataFormat)
    {
        Date d = new Date(longTime);
        SimpleDateFormat s = new SimpleDateFormat(dataFormat);
        return s.format(d);
    }


}
