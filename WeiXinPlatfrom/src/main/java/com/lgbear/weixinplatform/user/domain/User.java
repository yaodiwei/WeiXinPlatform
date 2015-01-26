package com.lgbear.weixinplatform.user.domain;

import java.io.Serializable;
import java.util.Date;

import com.lgbear.weixinplatform.base.domain.BaseDomain;

public class User extends BaseDomain {
	private String openId;
	private String nickName;
	private int sex;     //用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	private String country;
	private String province;
	private String city;
	private String headImgUrl;
	private int subscribeTime;
	
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public int getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(int subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public User() {
		super();
	}

	public User(String openId, String nickName, int sex, String country, String province, String city, String headImgUrl, int subscribeTime) {
		super();
		this.openId = openId;
		this.nickName = nickName;
		this.sex = sex;
		this.country = country;
		this.province = province;
		this.city = city;
		this.headImgUrl = headImgUrl;
		this.subscribeTime = subscribeTime;
	}
	
}
