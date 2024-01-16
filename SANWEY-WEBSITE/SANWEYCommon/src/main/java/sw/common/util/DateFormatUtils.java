/*
 * Copyright © 2016 uerp.net. All rights reserved.
 */
package sw.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.util.Date;

/**
 * @see org.apache.commons.lang3.time.DateFormatUtils
 * 
 * @author Bluear
 * @version 1.0
 */
public final class DateFormatUtils extends org.apache.commons.lang3.time.DateFormatUtils {
	
	/** 默认值 */
	private static final String DEFAULT_VALUE = StringUtils.EMPTY;
	
	/** yyyy-MM-dd */
	public static final FastDateFormat yyyyMmDd = ISO_DATE_FORMAT;

	/** yyyy-MM-dd HH:mm */
	public static final FastDateFormat yyyyMmDdHhMm = FastDateFormat.getInstance("yyyy-MM-dd HH:mm");
	
	/** HH:mm */
	public static final FastDateFormat hhMm = FastDateFormat.getInstance("HH:mm");

	/** 默认格式 */
	private static final FastDateFormat defaultFormat = yyyyMmDd;

	/**
	 * 不允许实例化
	 */
	private DateFormatUtils() {
	}
	
	/**
	 * 格式化日期
	 * 
	 * @param date 日期
	 * @return
	 */
	public static String format(final Date date) {
		return format(date, DEFAULT_VALUE);
	}
	
	/**
	 * 格式化日期
	 * 
	 * @param date 日期
	 * @param defaultValue 默认值
	 * @return
	 */
	public static String format(final Date date, final String defaultValue) {
		if (date == null) {
			return defaultValue;
		}
		
		return defaultFormat.format(date);
	}
	
	/**
	 * 格式化日期
	 * 
	 * @param date 日期
	 * @param format 格式
	 * @return
	 */
	public static String format(final Date date, final FastDateFormat format) {
		return format(date, DEFAULT_VALUE, format);
	}
	
	/**
	 * 格式化日期
	 * 
	 * @param date 日期
	 * @param defaultValue 默认值
	 * @param format 格式
	 * @return
	 */
	public static String format(final Date date, final String defaultValue, final String format) {
		if (date == null) {
			return defaultValue;
		}
		
		return FastDateFormat.getInstance(format).format(date);
	}
	
	/**
	 * 格式化日期
	 * 
	 * @param date 日期
	 * @param defaultValue 默认值
	 * @param format 格式
	 * @return
	 */
	public static String format(final Date date, final String defaultValue, final FastDateFormat format) {
		if (date == null) {
			return defaultValue;
		}
		
		return format.format(date);
	}
	
	/**
	 * 格式化日期
	 * 
	 * @param date 日期
	 * @param defaultValue 默认值
	 * @return
	 */
	public static String format(final Date date, final Date defaultValue) {
		return defaultFormat.format(date == null ? defaultValue : date);
	}
	
	/**
	 * 格式化日期
	 * 
	 * @param date 日期
	 * @param defaultValue 默认值
	 * @param format 格式
	 * @return
	 */
	public static String format(final Date date, final Date defaultValue, final String format) {
		return FastDateFormat.getInstance(format).format(date == null ? defaultValue : date);
	}
	
	/**
	 * 格式化日期
	 * 
	 * @param date 日期
	 * @param defaultValue 默认值
	 * @param format 格式
	 * @return
	 */
	public static String format(final Date date, final Date defaultValue, final FastDateFormat format) {
		return format.format(date == null ? defaultValue : date);
	}
	
	/**
	 * 格式化日期
	 * 
	 * @param date 日期
	 * @return
	 */
	public static String format(final long date) {
		return format(date, DEFAULT_VALUE);
	}
	
	/**
	 * 格式化日期
	 * 
	 * @param date 日期
	 * @param format 格式
	 * @return
	 */
	public static String format(final long date, final FastDateFormat format) {
		return format(date, DEFAULT_VALUE, format);
	}
	
	/**
	 * 格式化日期
	 * 
	 * @param date 日期
	 * @param defaultValue 默认值
	 * @return
	 */
	public static String format(final long date, final String defaultValue) {
		if (date <= 0) {
			return defaultValue;
		}
		
		return defaultFormat.format(date);
	}
	
	/**
	 * 格式化日期
	 * 
	 * @param date 日期
	 * @param defaultValue 默认值
	 * @param format 格式
	 * @return
	 */
	public static String format(final long date, final String defaultValue, final FastDateFormat format) {
		if (date <= 0) {
			return defaultValue;
		}
		
		return format.format(date);
	}
	
	/**
	 * 格式化日期
	 * 
	 * @param date 日期
	 * @param defaultValue 默认值
	 * @param format 格式
	 * @return
	 */
	public static String format(final long date, final String defaultValue, final String format) {
		if (date <= 0) {
			return defaultValue;
		}
		
		return FastDateFormat.getInstance(format).format(date);
	}
	
	/**
	 * 格式化日期
	 * 
	 * @param date 日期
	 * @param defaultValue 默认值
	 * @return
	 */
	public static String format(final long date, final long defaultValue) {
		return defaultFormat.format(date <= 0 ? defaultValue : date);
	}
	
	/**
	 * 格式化日期
	 * 
	 * @param date 日期
	 * @param defaultValue 默认值
	 * @param format 格式
	 * @return
	 */
	public static String format(final long date, final long defaultValue, final String format) {
		return FastDateFormat.getInstance(format).format(date <= 0 ? defaultValue : date);
	}
	
	/**
	 * 格式化日期
	 * 
	 * @param date 日期
	 * @param defaultValue 默认值
	 * @param format 格式
	 * @return
	 */
	public static String format(final long date, final long defaultValue, final FastDateFormat format) {
		return format.format(date <= 0 ? defaultValue : date);
	}
	
}
