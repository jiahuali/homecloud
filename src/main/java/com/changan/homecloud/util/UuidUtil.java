package com.changan.homecloud.util;

import java.util.UUID;

public class UuidUtil {

	/**
	 * 生成一个随机uuid
	 * 
	 * @return uuid
	 */
	public static String generateUuid() {
		String uuid = UUID.randomUUID().toString().replace("-", "").trim();
		if (uuid == null || uuid.equals(""))
			return null;
		return uuid;
	}

}
