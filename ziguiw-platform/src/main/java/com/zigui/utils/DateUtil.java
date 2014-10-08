package com.zigui.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期通用类
 * 
 * @copyright 湖南爱瑞杰科技发展股份有限公司
 * @author 唐其
 * @version 1.0
 * @since 2011年8月12日
 */
public class DateUtil {

	public static final int MILLIS_IN_SECOND = 1000;

	public static final int MILLIS_IN_MINUTE = 60 * 1000;

	public static final int MILLIS_IN_HOUR = 60 * 60 * 1000;

	public static final int MILLIS_IN_DAY = 24 * 60 * 60 * 1000;

	private static String mName[] = { "日", "一", "二", "三", "四", "五", "六" };

	public DateUtil() {
		super();
	}

	/**
	 * 当前的时间
	 * 
	 * @return
	 */
	public static Calendar getCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar;
	}

	/**
	 * 
	 * @param myDate
	 * @param format
	 * @return
	 */
	public static boolean isDateType(String myDate, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			sdf.parse(myDate);
		} catch (ParseException p) {
			return false;
		}
		return true;
	}

	/**
	 * 获得指定时间格式
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String setDateFormat(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 获得指定时间格式
	 * 
	 * @param myDate
	 * @param startFormat
	 * @param endFormat
	 * @return String
	 * @throws ParseException
	 */
	public static String setDateFormat(String myDate, String startFormat,
			String endFormat) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(startFormat);
		Date date = sdf.parse(myDate);
		sdf = new SimpleDateFormat(endFormat);
		String sDate = sdf.format(date);
		return sDate;
	}

	public static String toStringByFormat(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 获得日历对象
	 * 
	 * @param baseDate
	 * @param baseFormat
	 * @return Calendar
	 * @throws ParseException
	 */
	public static Calendar getCalendarInstance(String baseDate,
			String baseFormat) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(baseFormat);
		Date date = sdf.parse(baseDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	/**
	 * 获得日期对象
	 * 
	 * @param baseDate
	 * @param baseFormat
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateInstance(String baseDate, String baseFormat)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(baseFormat);
		Date date = sdf.parse(baseDate);
		return date;
	}

	/**
	 * 得到日期号
	 * 
	 * @param strDate
	 * @return int
	 * @throws ParseException
	 */
	public static int getDay(String strDate, String format)
			throws ParseException {
		Calendar cal = getCalendarInstance(strDate, format);
		return cal.get(Calendar.DATE);
	}

	/**
	 * @see 得到月份
	 * @param strDate
	 * @return int
	 * @throws ParseException
	 */
	public static int getMonth(String strDate, String format)
			throws ParseException {
		Calendar cal = getCalendarInstance(strDate, format);
		return cal.get(Calendar.MONTH) + 1;
	}

	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * @see 得到星期号
	 * @param strDate
	 * @param format
	 * @return int
	 * @throws ParseException
	 */
	public static int getWeekDay(String strDate, String format)
			throws ParseException {
		Calendar cal = getCalendarInstance(strDate, format);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	public static int getWeekDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 得到星期名称
	 * 
	 * @param strDate
	 * @param format
	 * @return string
	 * @throws ParseException
	 */
	public static String getWeekDayName(String strDate, String format)
			throws ParseException {
		int iWeek = getWeekDay(strDate, format);
		iWeek = iWeek - 1;
		return "星期" + mName[iWeek];
	}

	public static String getWeekDayName(Date date) {
		int iWeek = getWeekDay(date);
		iWeek = iWeek - 1;
		return "星期" + mName[iWeek];
	}

	/**
	 * 得到年份
	 * 
	 * @param strDate
	 * @param format
	 * @return int
	 * @throws ParseException
	 */
	public static int getYear(String strDate, String format)
			throws ParseException {
		Calendar cal = getCalendarInstance(strDate, format);
		return cal.get(Calendar.YEAR);
	}

	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 日期加法公式
	 * 
	 * @param strDate
	 * @param format
	 * @param iCount
	 * @param iCalendarType
	 * @return String
	 * @throws ParseException
	 */
	public static String DateAdd(String strDate, String format, int iCount,
			int iCalendarType) throws ParseException {
		Calendar Cal = getCalendarInstance(strDate, format);
		Cal.add(iCalendarType, iCount);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String sDate = sdf.format(Cal.getTime());
		return sDate;
	}

	/**
	 * 日期加法公式
	 * 
	 * @param strDate
	 * @param format
	 * @param iCount
	 * @param iCalendarType
	 * @return String
	 * @throws ParseException
	 */
	public static String DateAdd(Date dDate, String format, int iCount,
			int iCalendarType) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dDate);
		cal.add(iCalendarType, iCount);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String sDate = sdf.format(cal.getTime());
		return sDate;
	}

	/***************************************************************************
	 * 返回时间差
	 * 
	 * @param strDateBegin
	 * @param beginFomat
	 * @param strDateEnd
	 * @param endFormat
	 * @param iCalendarType
	 * @return int
	 * @throws ParseException
	 */
	public static int DateDiff(String strDateBegin, String beginformat,
			String strDateEnd, String endFormat, int iCalendarType)
			throws ParseException {
		Calendar calBegin = getCalendarInstance(strDateBegin, beginformat);
		Calendar calEnd = getCalendarInstance(strDateEnd, endFormat);
		long lBegin = calBegin.getTimeInMillis();
		long lEnd = calEnd.getTimeInMillis();
		Long lSS = lEnd - lBegin;

		int result = 0;

		switch (iCalendarType) {

		case Calendar.YEAR:
			throw new UnsupportedOperationException();
		case Calendar.MONTH:
			throw new UnsupportedOperationException();

		case Calendar.DAY_OF_YEAR:
			throw new UnsupportedOperationException();
		case Calendar.DATE:
			result = (int) (lSS / MILLIS_IN_DAY);
			break;
		case Calendar.HOUR_OF_DAY:
			result = (int) (lSS / MILLIS_IN_HOUR);
			break;
		case Calendar.MINUTE:
			result = (int) (lSS / MILLIS_IN_MINUTE);
			break;
		case Calendar.SECOND:
			result = (int) (lSS / MILLIS_IN_SECOND);
			break;
		case Calendar.MILLISECOND:
			break;// never useful
		default:
			throw new IllegalArgumentException("日历参数 " + iCalendarType
					+ " 是不被支持");
		}

		return result;
	}

	/***************************************************************************
	 * @功能 判断某年是否为闰年
	 * @return boolean
	 * @throws ParseException
	 */
	public static boolean isLeapYear(int yearNum) {
		boolean isLeep = false;
		if ((yearNum % 4 == 0) && (yearNum % 100 != 0)) {
			isLeep = true;
		} else if (yearNum % 400 == 0) {
			isLeep = true;
		} else {
			isLeep = false;
		}
		return isLeep;
	}

	/** */
	/**
	 * 取得某天是一年中的多少周
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(7);
		c.setTime(date);
		return c.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 取得某一年共有多少周
	 * 
	 * @param year
	 * @return
	 */
	public static int getMaxWeekNumOfYear(int year) {
		Calendar c = new GregorianCalendar();
		c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
		return getWeekOfYear(c.getTime());
	}

	/***************************************************************************
	 * @功能 计算指定日期某年的第几周
	 * @param strDate
	 * @param format
	 * @return int
	 * @throws ParseException
	 **************************************************************************/
	public static int getWeekNumOfYear(String strDate, String format)
			throws ParseException {
		Calendar calendar = getCalendarInstance(strDate, format);
		int iWeekNum = calendar.get(Calendar.WEEK_OF_YEAR);
		return iWeekNum;
	}

	public static int getWeekNumOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int iWeekNum = calendar.get(Calendar.WEEK_OF_YEAR);
		return iWeekNum;

	}

	/***************************************************************************
	 * @功能 计算某年某周的开始日期
	 * @param yearNum
	 * @param weekNum
	 * @param format
	 * @return String
	 * @throws ParseException
	 **************************************************************************/
	public static String getYearWeekFirstDay(int yearNum, int weekNum,
			String format) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, yearNum);
		cal.set(Calendar.WEEK_OF_YEAR, weekNum);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
	}

	/***************************************************************************
	 * @功能 计算某年某周的结束日期
	 * @param yearNum
	 * @param weekNum
	 * @param format
	 * @return String
	 * @throws ParseException
	 **************************************************************************/
	public static String getYearWeekEndDay(int yearNum, int weekNum,
			String format) throws ParseException {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, yearNum);
		cal.set(Calendar.WEEK_OF_YEAR, weekNum + 1);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
	}

	/***************************************************************************
	 * @功能 计算某年某月的结束日期
	 * @param yearNum
	 * @param monthNum
	 * @param format
	 * @return interger
	 * @throws ParseException
	 **************************************************************************/
	public static String getYearMonthEndDay(int yearNum, int monthNum,
			String format) throws ParseException {
		int tempDay = 31;
		if (monthNum == 1 || monthNum == 3 || monthNum == 5 || monthNum == 7
				|| monthNum == 8 || monthNum == 10 || monthNum == 12) {
			tempDay = 31;
		} else if (monthNum == 4 || monthNum == 6 || monthNum == 9
				|| monthNum == 11) {
			tempDay = 30;
		} else if (monthNum == 2) {
			if (isLeapYear(yearNum)) {
				tempDay = 29;
			} else {
				tempDay = 28;
			}
		}
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, yearNum);
		cal.set(Calendar.MONTH, monthNum - 1);
		cal.set(Calendar.DATE, tempDay);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
	}

	/**
	 * @功能 获得两个日期之间的天数
	 * @param yearbegin
	 * @param yearend
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static long getYearToYearEndDay(String yearbegin, String yearend,
			String format) throws ParseException {
		long DAY = 24L * 60L * 60L * 1000L;
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date d1 = df.parse(yearbegin);
		Date d2 = df.parse(yearend);
		return (long) ((long) (d2.getTime() - d1.getTime()) / DAY);
	}

	/* public static void main(String[] args) throws ParseException {
	 System.out.println(DateUtil.DateAdd("2009-07-14 12:00:00",
	 "yyyy-MM-dd HH:mm:ss", 20, Calendar.DAY_OF_MONTH));
	 System.out.println(DateUtil.DateAdd(new Date(),
	 "yyyy-MM-dd", 2, Calendar.MONTH));
	 System.out.println(DateUtil.DateAdd("2009-07-14 12:00:00",
	 "yyyy-MM-dd HH:mm:ss", 2, Calendar.YEAR));
			
	 }*/
}
