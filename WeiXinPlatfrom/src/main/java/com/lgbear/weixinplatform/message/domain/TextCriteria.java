package com.lgbear.weixinplatform.message.domain;

import com.lgbear.weixinplatform.base.domain.BaseCriteria;

public class TextCriteria extends BaseCriteria {
	
	private String content;
	private int createTimeBefore;
	private int createTimeAfter;
	private String openId;
	private String operatorId;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCreateTimeBefore() {
		return createTimeBefore;
	}

	public void setCreateTimeBefore(int createTimeBefore) {
		this.createTimeBefore = createTimeBefore;
	}

	public int getCreateTimeAfter() {
		return createTimeAfter;
	}

	public void setCreateTimeAfter(int createTimeAfter) {
		this.createTimeAfter = createTimeAfter;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public TextCriteria() {
		super();
	}

	public TextCriteria(String content, int createTimeBefore,
			int createTimeAfter, String openId, String operatorId) {
		super();
		this.content = content;
		this.createTimeBefore = createTimeBefore;
		this.createTimeAfter = createTimeAfter;
		this.openId = openId;
		this.operatorId = operatorId;
	}

}
