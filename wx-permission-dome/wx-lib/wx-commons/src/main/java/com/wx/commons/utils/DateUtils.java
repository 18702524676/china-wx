package com.wx.commons.utils;

import com.wx.commons.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

import static com.wx.commons.constant.CommonConstant.DATE_FORMAT.FORMAT1;
import static com.wx.commons.constant.CommonConstant.DATE_FORMAT.FORMAT18;

/**
 * @Author wx
 * @Description 日期时间工具类
 * @Date 2018-9-23
 */
@Slf4j
public class DateUtils {

	/**
	 * @methodName: parseString2Date
	 * @author: wx
	 * @description: 将日期字符串转换成日期类型
	 * @param dateStr
	 * @param format
	 * @date: 2018/9/23
	 * @return: java.util.Date
	 */
	public static Date parseString2Date(String dateStr, String format) {
		if (StringUtils.isBlank(dateStr) || StringUtils.isBlank(format)) {
			return null;
		}
		try {
			return org.apache.commons.lang3.time.DateUtils.parseDate(dateStr, format);
		} catch (ParseException e) {
			log.error("", e);
			return null;
		}
	}

	/**
	 * @methodName: parseTimestamp2Date
	 * @author: wx
	 * @description: 根据时间戳获取日期
	 * @param timestamp
	 * @date: 2018/9/23
	 * @return: java.util.Date
	 */
	public static Date parseTimestamp2Date(Long timestamp) {
		if (timestamp == null) {
			return null;
		}
		try {
			Long time = new Long(timestamp);
			SimpleDateFormat sdf = new SimpleDateFormat(CommonConstant.DATE_FORMAT.FORMAT4);
			return sdf.parse(sdf.format(time));
		} catch (Exception e) {
			log.error("", e);
			return null;
		}
	}

	/**
	 * @methodName: parseDate2String
	 * @author: wx
	 * @description: 将日期转换成日期字符串
	 * @param date
	 * @param format
	 * @date: 2018/9/23
	 * @return: java.lang.String
	 */
	public static String parseDate2String(Date date, String format) {
		if (date == null || StringUtils.isBlank(format)) {
			return null;
		}
		try {
			return DateFormatUtils.format(date, format);
		} catch (Exception e) {
			log.error("", e);
			return null;
		}
	}

	/**
	 * @methodName: getDatetimeStr
	 * @author: wx
	 * @description: 获取当前日期时间字符串，精确到毫秒
	 * @param
	 * @date: 2018/9/23
	 * @return: java.lang.String
	 */
	public static String getDatetimeStr() {
		return parseDate2String(new Date(), CommonConstant.DATE_FORMAT.FORMAT13);
	}

	/**
	 * @methodName: clearCalendar
	 * @author: wx
	 * @description: 设置日历函数初始值
	 * @param cal
	 * @date: 2018/9/23
	 * @return: void
	 */
	private static void clearCalendar(Calendar cal) {
		if (cal == null) {
			return;
		}
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
	}

	/**
	 * @methodName: getWeekFirstDate
	 * @author: wx
	 * @description: 获取指定日期所属周周一的日期
	 * @param date
	 * @date: 2018/9/23
	 * @return: java.util.Date
	 */
	public static Date getWeekFirstDate(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		clearCalendar(cal);
		int day_of_week = cal.get(Calendar.DAY_OF_WEEK);
		int num;
		if (1 == day_of_week) {
			num = -6;
		} else {
			num = -day_of_week + 2;
		}
		cal.add(Calendar.DATE, num);
		return cal.getTime();
	}

	/**
	 * @methodName: getWeekLastDate
	 * @author: wx
	 * @description: 获取指定日期所属周周日的日期
	 * @param date
	 * @date: 2018/9/23
	 * @return: java.util.Date
	 */
	public static Date getWeekLastDate(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		clearCalendar(cal);
		int day_of_week = cal.get(Calendar.DAY_OF_WEEK);
		int num;
		if (day_of_week == 1) {
			num = 0;
		} else {
			num = 7 - day_of_week + 1;
		}
		cal.add(Calendar.DATE, num);
		return cal.getTime();
	}

	/**
	 * @methodName: getMonthFirstDate
	 * @author: wx
	 * @description: 获取指定日期所属月份第一天的日期
	 * @param date
	 * @date: 2018/9/23
	 * @return: java.util.Date
	 */
	public static Date getMonthFirstDate(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		clearCalendar(cal);
		cal.add(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/**
	 * @methodName: getMonthLastDate
	 * @author: wx
	 * @description: 获取指定日期所属月份最后一天的日期
	 * @param date
	 * @date: 2018/9/23
	 * @return: java.util.Date
	 */
	public static Date getMonthLastDate(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		clearCalendar(cal);
		cal.add(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	/**
	 * @methodName: getMonthFirstDate
	 * @author: wx
	 * @description: 获取指定月份第一天的日期
	 * @param year
	 * @param month
	 * @date: 2018/9/23
	 * @return: java.util.Date
	 */
	public static Date getMonthFirstDate(Integer year, int month) {
		if (month <= 0 || month > 12) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		clearCalendar(calendar);
		if (null != year) {
			calendar.set(Calendar.YEAR, --year);
		}
		calendar.add(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * @methodName: getMonthLastDate
	 * @author: wx
	 * @description: 获取指定月份最后一天的日期
	 * @param year
	 * @param month
	 * @date: 2018/9/23
	 * @return: java.util.Date
	 */
	public static Date getMonthLastDate(Integer year, int month) {
		if (month <= 0 || month > 12) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		clearCalendar(calendar);
		if (null != year) {
			calendar.set(Calendar.YEAR, --year);
		}
		calendar.add(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/**
	 * @methodName: getQuarterFirstDate
	 * @author: wx
	 * @description: 获取指定日期所属季度第一天的日期
	 * @param date
	 * @date: 2018/9/23
	 * @return: java.util.Date
	 */
	public static Date getQuarterFirstDate(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.get(Calendar.MONTH);
		int month = calendar.get(Calendar.MONTH);
		if (month == 0 || month == 1 || month == 2) {
			// 第一季度
			calendar.set(Calendar.MONTH, 0);
		} else if (month == 3 || month == 4 || month == 5) {
			// 第二季度
			calendar.set(Calendar.MONTH, 3);
		} else if (month == 6 || month == 7 || month == 8) {
			// 第三季度
			calendar.set(Calendar.MONTH, 6);
		} else if (month == 9 || month == 10 || month == 11) {
			// 第四季度
			calendar.set(Calendar.MONTH, 9);
		}
		return getMonthFirstDate(calendar.getTime());
	}

	/**
	 * @methodName: getQuarterEndDate
	 * @author: wx
	 * @description: 获取指定日期所属季度最后一天的日期
	 * @param date
	 * @date: 2018/9/23
	 * @return: java.util.Date
	 */
	public static Date getQuarterEndDate(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.get(Calendar.MONTH);
		int month = calendar.get(Calendar.MONTH);
		if (month == 0 || month == 1 || month == 2) {
			// 第一季度
			calendar.set(Calendar.MONTH, 2);
		} else if (month == 3 || month == 4 || month == 5) {
			// 第二季度
			calendar.set(Calendar.MONTH, 5);
		} else if (month == 6 || month == 7 || month == 8) {
			// 第三季度
			calendar.set(Calendar.MONTH, 8);
		} else if (month == 9 || month == 10 || month == 11) {
			// 第四季度
			calendar.set(Calendar.MONTH, 11);
		}
		return getMonthLastDate(calendar.getTime());
	}

	/**
	 * @methodName: getYearFirstDate
	 * @author: wx
	 * @description: 获取指定日期所在年第一天的日期
	 * @param date
	 * @date: 2018/9/23
	 * @return: java.util.Date
	 */
	public static Date getYearFirstDate(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, 0);
		return getMonthFirstDate(calendar.getTime());
	}

	/**
	 * @methodName: getYearLastDate
	 * @author: wx
	 * @description: 获取指定日期所在年最后一天的日期
	 * @param date
	 * @date: 2018/9/23
	 * @return: java.util.Date
	 */
	public static Date getYearLastDate(Date date) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, 11);
		return getMonthLastDate(calendar.getTime());
	}

	/**
	 * @methodName: getWeekDates
	 * @author: wx
	 * @description: 获取指定日期所属周的所有日期（周一到周日日期列表）
	 * @param date
	 * @date: 2018/9/23
	 * @return: java.util.List<java.util.Date>
	 */
	public static List<Date> getWeekDates(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		clearCalendar(cal);
		List<Date> list = new ArrayList<>(7);
		int day_of_week = cal.get(Calendar.DAY_OF_WEEK);
		int num;
		if (day_of_week == 1) {
			num = -6;
		} else {
			num = -day_of_week + 2;
		}
		cal.add(Calendar.DATE, num);
		list.add(cal.getTime());
		for (int i = 0; i < 6; i++) {
			cal.add(Calendar.DATE, 1);
			list.add(cal.getTime());
		}
		return list;
	}

	/**
	 * @methodName: getMonthDates
	 * @author: wx
	 * @description: 获取指定日期所属月的所有日期（第一天到最后一天日期列表）
	 * @param date
	 * @date: 2018/9/23
	 * @return: java.util.List<java.util.Date>
	 */
	public static List<Date> getMonthDates(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		clearCalendar(cal);
		int monthDays = cal.getActualMaximum(Calendar.DATE);
		List<Date> list = new ArrayList<Date>(monthDays);
		int day_of_month = cal.get(Calendar.DAY_OF_MONTH);
		for (int i = 0; i < monthDays; i++) {
			cal.add(Calendar.DATE, i - day_of_month + 1);
			list.add(cal.getTime());
			cal.setTime(date);
		}
		return list;
	}

	/**
	 * @methodName: dateCalculation
	 * @author: wx
	 * @description: 获取指定日期num天之前或num天之后的日期
	 * @param date
	 * @param num
	 * @date: 2018/9/23
	 * @return: java.util.Date
	 */
	public static Date dateCalculation(Date date, int num) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		clearCalendar(cal);
		cal.add(Calendar.DATE, num);
		return cal.getTime();
	}

	/**
	 * @methodName: weekCalculation
	 * @author: wx
	 * @description: 获取指定日期num星期前或num星期后的日期
	 * @param date
	 * @param num
	 * @date: 2018/9/23
	 * @return: java.util.Date
	 */
	public static Date weekCalculation(Date date, int num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		clearCalendar(cal);
		cal.add(Calendar.WEEK_OF_MONTH, num);
		return cal.getTime();
	}

	/**
	 * @methodName: monthCalculation
	 * @author: wx
	 * @description: 获取指定日期num月前或num月后的日期
	 * @param date
	 * @param num
	 * @date: 2018/9/23
	 * @return: java.util.Date
	 */
	public static Date monthCalculation(Date date, int num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		clearCalendar(cal);
		cal.add(Calendar.MONTH, num);
		return cal.getTime();
	}

	/**
	 * @methodName: yearCalculation
	 * @author: wx
	 * @description: 获取指定日期num年前或num年后的日期
	 * @param date
	 * @param num
	 * @date: 2018/9/23
	 * @return: java.util.Date
	 */
	public static Date yearCalculation(Date date, int num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		clearCalendar(cal);
		cal.add(Calendar.YEAR, num);
		return cal.getTime();
	}

	/**
	 * @methodName: hourCalculation
	 * @author: wx
	 * @description: 获取指定日期num小时前或num小时后的日期
	 * @param date
	 * @param num
	 * @date: 2018/9/23
	 * @return: java.util.Date
	 */
	public static Date hourCalculation(Date date, int num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) + num);
		return cal.getTime();
	}

	/**
	 * 获取指定日期num分钟前或num分钟后的日期
	 *
	 * @param date
	 * @param num
	 * @return Date
	 * @author wupeng
	 * @date 2017年8月8日 下午2:47:09
	 */
	public static Date minuteCalculation(Date date, int num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + num);
		return cal.getTime();
	}

	/**
	 * @methodName: getChWeekName
	 * @author: wx
	 * @description: 获取指定日期的中文星期名
	 * @param date
	 * @date: 2018/9/23
	 * @return: java.lang.String
	 */
	public static String getChWeekName(Date date) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int week = c.get(Calendar.DAY_OF_WEEK);
		String weekName = null;
		switch (week) {
			case Calendar.MONDAY:
				weekName = "星期一";
				break;
			case Calendar.TUESDAY:
				weekName = "星期二";
				break;
			case Calendar.WEDNESDAY:
				weekName = "星期三";
				break;
			case Calendar.THURSDAY:
				weekName = "星期四";
				break;
			case Calendar.FRIDAY:
				weekName = "星期五";
				break;
			case Calendar.SATURDAY:
				weekName = "星期六";
				break;
			case Calendar.SUNDAY:
				weekName = "星期日";
				break;
			default:
				return null;
		}
		return weekName;
	}

	/**
	 * @methodName: getIntervalYears
	 * @author: wx
	 * @description: 获取两个日期相差的年数
	 * @param startDate
	 * @param endDate
	 * @date: 2018/9/23
	 * @return: int
	 */
	public static int getIntervalYears(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			return 0;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		int startYear = calendar.get(Calendar.YEAR);
		calendar.setTime(endDate);
		int endYear = calendar.get(Calendar.YEAR);
		return endYear - startYear;
	}

	/**
	 * @methodName: isSameDay
	 * @author: wx
	 * @description: 是否是同一天
	 * @param date1
	 * @param date2
	 * @date: 2018/9/23
	 * @return: boolean
	 */
	public static boolean isSameDay(Date date1, Date date2) {
		String date1Str = parseDate2String(date1, FORMAT1);
		String date2Str = parseDate2String(date2, FORMAT1);
		return Objects.equals(date1Str, date2Str);
	}

	/**
	 * @param
	 * @methodName: getTwelveMonth
	 * @author: wx
	 * @description: 获取今年1-12个月的日期数组
	 * @date: 2018/9/23
	 * @return: java.util.Date[]
	 */
	public static Date[] getTwelveMonth() {
		Calendar calendar = Calendar.getInstance();
		// 获取年
		int year = calendar.get(Calendar.YEAR);
		// 存储1-12月日期容器
		Date[] twelveMonth = new Date[12];
		for (int i = 0; i < 12; i++) {
			twelveMonth[i] = parseString2Date(year + "-" + (i + 1), FORMAT18);
		}
		return twelveMonth;
	}

	/**
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param format 返回时间格式
	 * @methodName: getStartTimeGoEndTimeBetween
	 * @author: wx
	 * @description: 开始时间到结束时间的日期集合(包含开始时间与结束时间)
	 * @date: 2018/9/23
	 * @return: java.util.List<java.util.Date>
	 */
	public static List<Date> getStartTimeGoEndTimeBetween(Date startTime, Date endTime, String format) {
		List<Date> result = new ArrayList<Date>();
		Calendar tempStart = Calendar.getInstance();
		tempStart.setTime(getFormatDate(startTime, format));
		tempStart.add(Calendar.DAY_OF_YEAR, 0);

		Calendar tempEnd = Calendar.getInstance();
		tempEnd.setTime(getFormatDate(endTime, format));
		while (tempStart.before(tempEnd) || tempStart.equals(tempEnd)) {
			result.add(tempStart.getTime());
			tempStart.add(Calendar.DAY_OF_YEAR, 1);
		}
		return result;
	}

	/**
	 * @param now 时间
	 * @param format 时间格式
	 * @methodName: getFormatDate
	 * @author: wx
	 * @description: 返回指定时间格式的Date
	 * @date: 2018/9/23
	 * @return: java.util.Date
	 */
	public static Date getFormatDate(Date now, String format) {
		return parseString2Date(parseDate2String(now, format), format);
	}

	/**
	 * @param now 时间
	 * @methodName: dateConversionCron_HHmmss
	 * @author: wx
	 * @description: 时间转换Cron表达式 规则每天的HHmmss(用于定时器)
	 * @date: 2018/9/23
	 * @return: java.lang.String
	 */
	public static String dateConversionCron_HHmmss(Date now) {
		StringBuilder stringBuilder = new StringBuilder();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		stringBuilder.append(String.valueOf(calendar.get(Calendar.SECOND))).append(" ")
			.append(String.valueOf(calendar.get(Calendar.MINUTE))).append(" ")
			.append(String.valueOf(calendar.get(Calendar.HOUR_OF_DAY))).append(" ").append("*").append(" ").append("*")
			.append(" ").append("?");
		return stringBuilder.toString();
	}

	/**
	 * @param year 年
	 * @methodName: getYearAllDate
	 * @author: wx
	 * @description: 根据年来获取一年的全部日期集合
	 * @date: 2018/9/23
	 * @return: java.util.List<java.lang.String>
	 */
	public static List<String> getYearAllDate(int year) {
		List<String> dateList = new ArrayList<>(370);
		Calendar c = Calendar.getInstance();
		int month = 0;
		int day = 0;
		for (int i = 0; i < 12; i++) {
			c.set(year, i, 1);
			int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
			for (int x = 1; x <= lastDay; x++) {
				StringBuilder yearMonthDay = new StringBuilder();
				month = i + 1;
				day = x;
				yearMonthDay.append(year).append("-").append(month > 9 ? month : "0" + month).append("-")
					.append((day > 9 ? day : "0" + day));
				dateList.add(yearMonthDay.toString());
			}
		}
		return dateList;
	}

	/**
	 * @param now 日期
	 * @methodName: getYear
	 * @author: wx
	 * @description: 获取指定日期的年
	 * @date: 2018/9/23
	 * @return: int
	 */
	public static int getYear(Date now) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(now);
		return ca.get(Calendar.YEAR);
	}

	/**
	 * @param now 日期
	 * @methodName: getMonth
	 * @author: wx
	 * @description: 获取指定日期月
	 * @date: 2018/9/23
	 * @return: int
	 */
	public static int getMonth(Date now) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(now);
		return ca.get(Calendar.MONTH);
	}

	/**
	 * @param now 日期
	 * @methodName: getDay
	 * @author: wx
	 * @description: 获取指定日期日
	 * @date: 2018/9/23
	 * @return: int
	 */
	public static int getDayOfMonth(Date now) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(now);
		return ca.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @param datePattern 时间格式
	 * @methodName: isRightDateStr
	 * @author: wx
	 * @description: 验证开始时间and结束时间是否符合指定时间格式
	 * @date: 2018/9/23
	 * @return: boolean
	 */
	public static boolean isRightDateStr(String startDate, String endDate, String datePattern) {
		return isRightDateStr(startDate, datePattern) && isRightDateStr(endDate, datePattern);
	}

	/**
	 * @param dateStr 时间字符串
	 * @param datePattern 时间格式
	 * @methodName: isRightDateStr
	 * @author: wx
	 * @description: 判断是否是对应的格式的日期字符串
	 * @date: 2018/9/23
	 * @return: boolean
	 */
	public static boolean isRightDateStr(String dateStr, String datePattern) {
		DateFormat dateFormat = new SimpleDateFormat(datePattern);
		try {
			// 采用严格的解析方式，防止类似 “2018-05-35 and 2018-15-11” 类型的字符串通过
			dateFormat.setLenient(false);
			dateFormat.parse(dateStr);
			Date date = (Date) dateFormat.parse(dateStr);
			// 重复比对一下，防止类似 “2018-5-15” 类型的字符串通过
			String newDateStr = dateFormat.format(date);
			if (dateStr.equals(newDateStr)) {
				return true;
			} else {
				// System.out.println(String.format("字符串dateStr%s不是严格的 datePattern格式的字符串%s" + dateStr, datePattern));
				return false;
			}
		} catch (Exception e) {
			log.error("", e);
			// System.out.println(String.format("字符串dateStr:%s不能按照 atePattern:%s" + dateStr, datePattern) + "样式转换");
			return false;
		}
	}

	/**
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @methodName: getStartTimeGoEndTimeBetweenMonthNum
	 * @author: wx
	 * @description: 获取开始时间到结束时间的总月数(只要满30天就符合一个月)
	 * @date: 2018/9/23
	 * @return: int
	 */
	public static int getStartTimeGoEndTimeBetweenMonthNum(Date startTime, Date endTime) {
		int month = 12;
		int monthNum = 0;
		Period between = Period.between(
			LocalDate.parse(parseDate2String(startTime, parseDate2String(startTime, FORMAT1))),
			LocalDate.parse(parseDate2String(startTime, parseDate2String(endTime, FORMAT1))));
		monthNum = between.getMonths();
		// 如果年数大于0，年数*月数
		if (between.getYears() > 0) {
			monthNum += (between.getYears() * month);
		}
		System.out.println(String.format("%d年%d月,总月数%d", between.getYears(), between.getMonths(), monthNum));
		return monthNum;
	}

	/**
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @methodName: getStartTimeGoEndTimeBetweenMonthNum
	 * @author: wx
	 * @description: 获取开始时间到结束时间的总月数(必须是完整月)
	 * @date: 2018/9/23
	 * @return: int
	 */
	public static int getStartTimeGoEndTimeBetweenMonthNumV2(Date startTime, Date endTime) {
		String starMonth = parseDate2String(startTime, FORMAT18);
		String endMonth = parseDate2String(endTime, FORMAT18);
		// 如果是同一个月
		if (Objects.equals(starMonth, endMonth)) {
			return 0;
		}
		// 开始时间获取day
		int starDay = getDayOfMonth(startTime);
		// 结束时间获取day
		int endDay = getDayOfMonth(endTime);
		// 开始时间本月最后day
		// int starLastDay = getDayOfMonth(getMonthLastDate(startTime));
		// 结束时间本月最后day
		int endLstDay = getDayOfMonth(getMonthLastDate(endTime));
		int countdDifferMonth = 0;

		if (starDay > 1) {
			countdDifferMonth += 1;
		}
		if (endDay > 1 && endDay < endLstDay) {
			countdDifferMonth += 1;
		}
		// 开始时间
		Calendar start = Calendar.getInstance();
		start.setTime(startTime);
		// 结束时间
		Calendar end = Calendar.getInstance();
		end.setTime(endTime);
		int subMonthCount = 0;
		if (!start.after(end)) {
			subMonthCount = (end.get(Calendar.YEAR) - start.get(Calendar.YEAR) == 0) ?
			// 同一年
				end.get(Calendar.MONTH) - start.get(Calendar.MONTH)
				// 年数差超过2年
				: ((end.get(Calendar.YEAR) - start.get(Calendar.YEAR) >= 2)
					? (end.get(Calendar.YEAR) - start.get(Calendar.YEAR) - 1) * 12
						+ start.getActualMaximum(Calendar.MONTH) - start.get(Calendar.MONTH) + end.get(Calendar.MONTH)
						+ 1
					:
					// 年数差为1，Calendar.get(MONTH) 第一月是0，所以+1
					start.getActualMaximum(Calendar.MONTH) - start.get(Calendar.MONTH) + end.get(Calendar.MONTH) + 1);
			System.out.println(subMonthCount - countdDifferMonth);

		}
		return subMonthCount - countdDifferMonth;
	}

	/**
	 * @methodName: validationStartTimeAndEndTime
	 * @author: wx
	 * @description: 验证开始时间是否是开始时间当月第一天 与结束时间是否是结束时间当月最后一天，并且是同一月
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @date: 2018/9/23
	 * @return: boolean
	 */
	public static boolean validationStartTimeAndEndTime(Date startTime, Date endTime) {
		if (getYear(startTime) != getYear(endTime)) {
			return false;
		} else if (getMonth(startTime) != getMonth(endTime)) {
			return false;
		}
		return getDayOfMonth(startTime) == getDayOfMonth(getMonthFirstDate(startTime))
			&& getDayOfMonth(endTime) == getDayOfMonth(getMonthLastDate(endTime));
	}

	/**
	 * @methodName: checkDateIsMonthFirstDay
	 * @author: wx
	 * @description: 检查日期是否是所在月的第一天
	 * @param beginDate
	 * @date: 2018/9/23
	 * @return: boolean
	 */
	public static boolean checkDateIsMonthFirstDay(Date beginDate) {
		if (beginDate == null) {
			return false;
		}
		Date monthFirstDate = getMonthFirstDate(beginDate);
		if (getDayOfMonth(beginDate) == getDayOfMonth(monthFirstDate)) {
			return true;
		}
		return false;
	}

	/**
	 * @methodName: checkDateIsMonthLastDay
	 * @author: wx
	 * @description: 检查日期是否是所在月的最后一天
	 * @param endDate
	 * @date: 2018/9/23
	 * @return: boolean
	 */
	public static boolean checkDateIsMonthLastDay(Date endDate) {
		if (endDate == null) {
			return false;
		}
		Date monthEndDate = getMonthLastDate(endDate);
		if (getDayOfMonth(endDate) == getDayOfMonth(monthEndDate)) {
			return true;
		}
		return false;
	}

	/**
	 * @methodName: getNumBeforeAndAfterDay
	 * @author: wx
	 * @description: 获取monthNum月前or月后的多少号
	 * @param now
	 * @param monthNum
	 * @param day
	 * @date: 2018/9/23
	 * @return: java.util.Date
	 */
	public static Date getNumBeforeAndAfterDay(Date now, int monthNum, int day) {
		if (now == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		clearCalendar(cal);
		cal.add(Calendar.MONTH, monthNum);
		cal.set(Calendar.DAY_OF_MONTH, day);
		return cal.getTime();
	}

	/**
	 * @methodName: isEffectiveDate
	 * @author: wx
	 * @description: 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
	 * @param nowTime
	 * @param startTime
	 * @param endTime
	 * @date: 2018/9/23
	 * @return: boolean
	 */
	public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
		if (nowTime.getTime() == startTime.getTime() || nowTime.getTime() == endTime.getTime()) {
			return true;
		}

		Calendar date = Calendar.getInstance();
		date.setTime(nowTime);
		Calendar begin = Calendar.getInstance();
		begin.setTime(startTime);
		Calendar end = Calendar.getInstance();
		end.setTime(endTime);

		if (date.after(begin) && date.before(end)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @description: 比较时间大小
	 * @author: wx
	 * @date: 2018/9/23 0018
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param format 时间格式
	 * @return: int 1=大于 0=等于 -1=小于
	 */
	public static int dateComparator(Date startTime, Date endTime, String format) {
		if (parseDate2String(startTime, format).compareTo(parseDate2String(endTime, format)) > 0) {
			return 1;
		} else if (parseDate2String(startTime, format).compareTo(parseDate2String(endTime, format)) == 0) {
			return 0;
		} else {
			return -1;
		}
	}

	/**
	 * @description: now比较开始时间and结束时间
	 * @author: wx
	 * @date: 2018/9/23
	 * @param now
	 * @param startTime
	 * @param endTime
	 * @param format
	 * @return: boolean
	 */
	public static boolean dateComparator(Date now, Date startTime, Date endTime, String format) {

		if (startTime != null && DateUtils.dateComparator(now, startTime, format) < 0) {
			return true;
		}

		if (endTime != null && DateUtils.dateComparator(now, endTime, format) > 0) {
			return true;
		}
		return false;
	}
}
