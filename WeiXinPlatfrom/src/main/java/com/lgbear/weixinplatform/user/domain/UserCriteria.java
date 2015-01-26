package com.lgbear.weixinplatform.user.domain;

import com.lgbear.weixinplatform.base.domain.BaseCriteria;

public class UserCriteria extends BaseCriteria {
	private String openId;
	private String nickName;

	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}

}
