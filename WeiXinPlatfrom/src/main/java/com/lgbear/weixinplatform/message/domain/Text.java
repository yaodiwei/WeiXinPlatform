package com.lgbear.weixinplatform.message.domain;

import java.text.SimpleDateFormat;

import com.lgbear.weixinplatform.base.domain.BaseDomain;

public class Text extends BaseDomain {

	private String msgId;
	private String content;
	private int createTime;
	private String openId;
	private String operatorId;
	
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCreateTime() {
		return createTime;
	}
	public void setCreateTime(int createTime) {
		this.createTime = createTime;
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
	
	public Text() {
		super();
	}
	public Text(String msgId, String content, int createTime, String openId, String operatorId) {
		super();
		this.msgId = msgId;
		this.content = content;
		this.createTime = createTime;
		this.openId = openId;
		this.operatorId = operatorId;
	}
	
	public String getCreateTimeStr() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime*1000L);
	}
}
