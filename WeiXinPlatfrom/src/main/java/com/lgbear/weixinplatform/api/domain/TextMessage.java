package com.lgbear.weixinplatform.api.domain;

public class TextMessage extends WeixinMessage {

	private String msgType = WeixinMessage.TEXT;
	private String content;
	private String msgId;

	public TextMessage() {
	}

	public TextMessage(String msgType, String content, String msgId) {
		super();
		this.msgType = msgType;
		this.content = content;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
}
