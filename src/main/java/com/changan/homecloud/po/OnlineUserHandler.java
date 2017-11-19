package com.changan.homecloud.po;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class OnlineUserHandler {

	private HashMap<String, OnlineUser> onlineUserMap;

	private static Logger logger = Logger.getLogger(OnlineUserHandler.class);

	private static Integer offlineMills = 12 * 60 * 60;
	// private static Integer offlineMills = 8;

	public OnlineUserHandler() {
		onlineUserMap = new HashMap<>();

		new Thread() {
			public void run() {
				while (true) {
					Set<String> keySet = onlineUserMap.keySet();
					Iterator<String> iterator = keySet.iterator();
					while (iterator.hasNext()) {

						String key = iterator.next();
						Long gap = new Date().getTime() - onlineUserMap.get(key).getLastOperateTime().getTime();
						if (gap > offlineMills * 1000) {
							iterator.remove();
							onlineUserMap.remove(key);
							System.out.println("token已失效，token:" + key);
							logger.info("token已失效，token:" + key);
						}
					}
					// 两秒检查一次
					try {
						Thread.sleep(offlineMills * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();

	}

	public OnlineUser getOnlineUserByToken(String token) {
		return onlineUserMap.get(token);
	}

	public void addOnlineUserByToken(String token, OnlineUser onlineUser) {
		logger.info("用户上线,phone:" + onlineUser.getPhone());
		System.out.println("用户上线,phone:" + onlineUser.getPhone());
		onlineUser.setLastOperateTime(new Date());
		onlineUserMap.put(token, onlineUser);
	}

	/**
	 * 更新最近时间
	 * 
	 * @param tuid
	 */
	public void updateOnlineUserByToken(String token) {

		OnlineUser onlineUser = getOnlineUserByToken(token);
		onlineUser.setLastOperateTime(new Date());
	}

}
