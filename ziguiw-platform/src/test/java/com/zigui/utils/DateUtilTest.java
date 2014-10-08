package com.zigui.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * long类型时间格式化
 * @author zhouping
 *
 */
public class DateUtilTest {
	
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


	private static long stringToLong(String sourceTime, String dataFormat) {
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
	
	/**
	 * 测试
	 */
	public static void timeTest() {
		Date date = new Date();
		long longtime = date.getTime();
		System.out.println("long型时间：" + longtime);
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat format3 = new SimpleDateFormat("yyyy年MM月dd日");
		SimpleDateFormat format4 = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat format5 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format6 = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat format7 = new SimpleDateFormat("yyyy");
		System.out.println(format1.format(longtime));
		System.out.println(format2.format(longtime));
		System.out.println(format3.format(longtime));
		System.out.println(format4.format(longtime));
		System.out.println(format5.format(longtime));
		System.out.println(format6.format(longtime));
		System.out.println(format7.format(longtime));
	}
	
	public static void main(String[] args) {
		String strTime = "2011年08月02日 17:29";
		String dataFormat = "yyyy年MM月dd日 HH:mm";
		// 将 "2011年07月02日 03:10"的形式转化成 "1309574632778" 的格式
		long longTime = stringToLong(strTime, dataFormat);
		System.out.println(strTime + "->" + longTime);
		dataFormat = "yyyy-MM-dd HH:mm:ss";
		// 1261128540000->2011年07月02日 17:29:00
		strTime = longToString(longTime, dataFormat);
		System.out.println(longTime + "->" + strTime);
		timeTest();

	}
}
