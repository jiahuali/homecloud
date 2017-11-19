package com.changan.homecloud.util;

/**
 * String工具类
 * 
 * @author ljh
 *
 */
public class StringUtils {
	/**
	 * String为null或者去除空格后为空串
	 * 
	 * @param str,不定参数支持
	 * @return
	 */
	public static boolean isNull(String... str) {
		for (String string : str) {
			if (string == null || string.trim().equals(""))
				return true;
		}
		return false;
	}

}
