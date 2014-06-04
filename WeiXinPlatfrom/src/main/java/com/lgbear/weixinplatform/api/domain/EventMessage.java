package com.lgbear.weixinplatform.api.domain;

public class EventMessage extends WeixinMessage {

	protected String event;
	protected String eventKey;
	public final static String SUBSCRIBE = "subscribe";
	public final static String CLICK = "CLICK";
	public final static String LOCATION = "LOCATION";

	public EventMessage() {
		msgType = WeixinMessage.EVENT;
	}

	public EventMessage(String msgType, String event, String eventKey) {
		msgType = WeixinMessage.EVENT;
		this.event = event;
		this.eventKey = eventKey;
	}

	public final String getEvent() {
		return event;
	}

	public final void setEvent(String event) {
		this.event = event;
	}

	public final String getEventKey() {
		return eventKey;
	}

	public final void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
}
