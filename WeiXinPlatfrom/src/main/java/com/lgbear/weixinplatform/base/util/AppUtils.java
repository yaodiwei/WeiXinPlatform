package com.lgbear.weixinplatform.base.util;

import java.text.ParseException;
import java.util.Date;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

public class AppUtils {
	public static String name() {
		return "微信平台";
	}

	public static String version() {
		return "v1.0.0.0000";
	}
	
	public static long time() {
		return new Date().getTime();
	}

	public static Date getInitDate() {
		return initDate;
	}

	private static Date initDate = null;
	static {
		try {
			initDate = DateUtils.parseDate("2000-01-01", "yyyy-MM-dd");
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	
	
	
	
	
	private static Log exceptionLog = LogFactory.getLog("ExceptionLog");

	/**
	 * 记录日志
	 * 
	 * @param e
	 */
	public static void recordException(Exception e) {
		e.printStackTrace();
		exceptionLog.error(e.getMessage(), e);
	}

	/**
	 * 获取MD5密文
	 * 
	 * @param input
	 * @return
	 */
	public static String md5Hex(String input) {
		return DigestUtils.md5Hex(input);
	}

	/**
	 * 生成随机数验证码
	 * 
	 * @return
	 */
	public static String generateValidateCode(int length) {
		Random r = new Random();
		if (length <= 0)
			length = 6;
		String validateCode = "";
		for (int i = 0; i < length; i++) {
			validateCode += r.nextInt(9);
		}
		return validateCode;
	}

	public static int parseInt(String value) {
		Assert.hasText(value, "Parse int value must not be null or empty!");
		try {
			return Integer.parseInt(value.trim());
		} catch (Exception e) {
			throw new IllegalArgumentException(value + " can not parse int!");
		}
	}

	public static int parseInt(String value, int defualtValue) {
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
		}
		return defualtValue;
	}
}
