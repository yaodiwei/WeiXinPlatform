package com.lgbear.weixinplatform.message.domain;

import com.lgbear.weixinplatform.base.domain.BaseDomain;

public class Passive extends BaseDomain {

	private String operatorId;
	private String name;
	private String value;
	private int enable;
	
	
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public Passive() {
		super();
	}
	public Passive(String operatorId, String name, String value, int enable) {
		super();
		this.operatorId = operatorId;
		this.name = name;
		this.value = value;
		this.enable = enable;
	}
}
