package com.lgbear.weixinplatform.api.domain;

public class LocationMessage extends WeixinMessage {

	private String msgType = WeixinMessage.LOCATION;
	private String location_X;
	private String Location_Y;
	private String scale;
	private String label;
	private String msgId;

	@Override
	public String getMsgType() {
		return msgType;
	}

	@Override
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getLocation_X() {
		return location_X;
	}

	public void setLocation_X(String location_X) {
		this.location_X = location_X;
	}

	public String getLocation_Y() {
		return Location_Y;
	}

	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
}
