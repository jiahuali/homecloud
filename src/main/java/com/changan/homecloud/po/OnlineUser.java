package com.changan.homecloud.po;

import java.util.Date;

public class OnlineUser extends User {

	private Date lastOperateTime;

	public Date getLastOperateTime() {
		return lastOperateTime;
	}

	public void setLastOperateTime(Date lastOperateTime) {
		this.lastOperateTime = lastOperateTime;
	}

}
