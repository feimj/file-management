/*
 * @(#)DateUtils.java    2018年6月14日
 * 
 * Copyright (c) 2018, GuangZhou HeMinJian Electronic Technology Co.,LTD. All rights reserved.
 * GuangZhou HeMinJian Electronic Technology Co.,LTD. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.heminjian.file_management.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * ClassName: DateUtils.<br>
 * Description: 时间操作工具类.<br>
 * Date: 2018年6月14日
 * @author HeMinJian
 * @version 1.0.0
 * @since JDK 1.7
 */
public class DateUtils {

    /**
     * Description: 将字符串日期转换成java.util.Date（yyyy-MM-dd HH:mm:ss）.<br>
     * Date: 2018年6月14日
     * @author HeMinJian
     * @param dateString 日期字符串
     * @return 格式化的日期，格式为 yyyy-MM-dd HH:mm:ss
     */
	public static Date stringToDate(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return date;
	}
	
	/**
	 * Description: 将字符串日期转换成java.util.Date（yyyy-MM-dd）.<br>
	 * Date: 2018年6月14日
	 * @author HeMinJian
	 * @param dateString 日期字符串
	 * @return 格式化的日期，格式为 yyyy-MM-dd
	 */
	public static Date stringToDate1(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return date;
	}
	
	/**
	 * Description: 将字符串日期转换成java.sql.Timestamp.<br>
	 * Date: 2018年6月14日
	 * @author HeMinJian
	 * @param dateString 日期字符串
	 * @return 字符串对应的Timestamp
	 */
	public static Timestamp stringToTimeStamp(String dateString) {
		if (StringUtils.isNotEmpty(dateString)) {
		    Timestamp date = Timestamp.valueOf(dateString);
		    return date;
		} else {
			return null;
		}
	}
	
	/**
	 * Description: 将日期转换为字符串（yyyy-MM-dd HH:mm:ss）.<br>
	 * Date: 2018年6月14日
	 * @author HeMinJian
	 * @param date 日期
	 * @return 日期对应的字符串，格式为 yyyy-MM-dd HH:mm:ss
	 */
	public static String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = sdf.format(date);
		return dateString;
	}
	
	/**
	 * Description: 将日期转换为字符串（yyyy-MM-dd）.<br>
	 * Date: 2018年6月14日
	 * @author HeMinJian
	 * @param date 日期
	 * @return 日期对应的字符串，格式为 yyyy-MM-dd
	 */
	public static String dateToString1(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = sdf.format(date);
		return dateString;
	}
	
}
