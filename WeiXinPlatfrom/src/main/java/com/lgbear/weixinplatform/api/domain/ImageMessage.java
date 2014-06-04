package com.lgbear.weixinplatform.api.domain;

public class ImageMessage extends WeixinMessage {

	private String msgType = WeixinMessage.IMAGE;
	private String picUrl;
	private String msgId;

	public ImageMessage() {
	}

	public ImageMessage(String msgType, String picUrl, String msgId) {
		super();
		this.msgType = msgType;
		this.picUrl = picUrl;
		this.msgId = msgId;
	}

	@Override
	public String getMsgType() {
		return msgType;
	}

	@Override
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
}
