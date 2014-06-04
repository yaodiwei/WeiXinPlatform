package com.lgbear.weixinplatform.api.service;

import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lgbear.weixinplatform.api.domain.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MessageService {
	protected Logger log = LoggerFactory.getLogger(getClass());

	public WeixinMessage loadMessageFormXML(Document document, HttpServletResponse response) {
		log.info("method loadMessageFormXML start");
		if (document == null) {
			return null;
		}
		Element root = document.getRootElement();
		String ToUserName = root.elementText("ToUserName");
		String FromUserName = root.elementText("FromUserName");
		String CreateTime = root.elementText("CreateTime");
		if (CreateTime == null || "".equals(CreateTime.trim())) {
			CreateTime = (System.currentTimeMillis() + "").substring(0, 10);
		}
		String MsgType = root.elementText("MsgType");
		WeixinMessage weixinMessage;
		if (MsgType == null || "".equals(MsgType.trim())) {
			return null;
		}
		if (WeixinMessage.TEXT.equals(MsgType.trim())) {
			weixinMessage = loadTextMessage(ToUserName, FromUserName, CreateTime, root, response);
		} else if (WeixinMessage.IMAGE.equals(MsgType.trim())) {
			weixinMessage = loadImageMessage(ToUserName, FromUserName, CreateTime, root);
		} else if (WeixinMessage.LOCATION.equals(MsgType.trim())) {
			weixinMessage = loadLocationMessage(ToUserName, FromUserName, CreateTime, root);
		} else if (WeixinMessage.LINK.equals(MsgType.trim())) {
			weixinMessage = loadLinkMessage(ToUserName, FromUserName, CreateTime, root);
		} else if (WeixinMessage.EVENT.equals(MsgType.trim())) {
			weixinMessage = loadEventMessage(ToUserName, FromUserName, CreateTime, root);
		} else {
			return null;
		}
		log.info("\n" + JSONObject.fromObject(weixinMessage).toString(2));
		return weixinMessage;
	}

	private WeixinMessage loadTextMessage(String ToUserName, String FromUserName, String CreateTime, Element root, HttpServletResponse response) {
		TextMessage weixinMessage = new TextMessage();
		weixinMessage.setToUserName(ToUserName);
		weixinMessage.setFromUserName(FromUserName);
		weixinMessage.setCreateTime(Integer.parseInt(CreateTime));
		weixinMessage.setContent(root.elementText("Content"));
		weixinMessage.setMsgId(root.elementText("MsgId"));
		sendText(ToUserName, FromUserName, CreateTime, response, "你好");
		return (WeixinMessage) weixinMessage;
	}

	private WeixinMessage loadImageMessage(String ToUserName, String FromUserName, String CreateTime, Element root) {
		ImageMessage weixinMessage = new ImageMessage();
		weixinMessage.setToUserName(ToUserName);
		weixinMessage.setFromUserName(FromUserName);
		weixinMessage.setCreateTime(Integer.parseInt(CreateTime));
		weixinMessage.setPicUrl(root.elementText("PicUrl"));
		weixinMessage.setMsgId(root.elementText("MsgId"));
		return (WeixinMessage) weixinMessage;
	}

	private WeixinMessage loadLocationMessage(String ToUserName, String FromUserName, String CreateTime, Element root) {
		LocationMessage weixinMessage = new LocationMessage();
		weixinMessage.setToUserName(ToUserName);
		weixinMessage.setFromUserName(FromUserName);
		weixinMessage.setCreateTime(Integer.parseInt(CreateTime));
		weixinMessage.setLocation_X(root.elementText("Location_X"));
		weixinMessage.setLocation_Y(root.elementText("Location_Y"));
		weixinMessage.setScale(root.elementText("Scale"));
		weixinMessage.setLabel(root.elementText("Label"));
		weixinMessage.setMsgId(root.elementText("MsgId"));
		return (WeixinMessage) weixinMessage;
	}

	private WeixinMessage loadLinkMessage(String ToUserName, String FromUserName, String CreateTime, Element root) {
		LinkMessage weixinMessage = new LinkMessage();
		weixinMessage.setToUserName(ToUserName);
		weixinMessage.setFromUserName(FromUserName);
		weixinMessage.setCreateTime(Integer.parseInt(CreateTime));
		weixinMessage.setTitle(root.elementText("Title"));
		weixinMessage.setDescription(root.elementText("Description"));
		weixinMessage.setUrl(root.elementText("Url"));
		weixinMessage.setMsgId(root.elementText("MsgId"));
		return (WeixinMessage) weixinMessage;
	}

	private WeixinMessage loadEventMessage(String ToUserName, String FromUserName, String CreateTime, Element root) {
		String event = root.elementText("Event");
		if (EventMessage.LOCATION.equals(event)) {
			LocationEventMessage locationEventMessage = new LocationEventMessage();
			locationEventMessage.setToUserName(ToUserName);
			locationEventMessage.setFromUserName(FromUserName);
			locationEventMessage.setCreateTime(Integer.parseInt(CreateTime));
			locationEventMessage.setEvent(event);
			locationEventMessage.setLatitude(root.elementText("Latitude"));
			locationEventMessage.setLongitude(root.elementText("Longitude"));
			locationEventMessage.setPrecision(root.elementText("Precision"));
			return locationEventMessage;
		} else {
			EventMessage weixinMessage = new EventMessage();
			weixinMessage.setToUserName(ToUserName);
			weixinMessage.setFromUserName(FromUserName);
			weixinMessage.setCreateTime(Integer.parseInt(CreateTime));
			weixinMessage.setEvent(root.elementText("Event"));
			weixinMessage.setEventKey(root.elementText("EventKey"));
			return (WeixinMessage) weixinMessage;
		}
	}

	public void sendText(String FromUserName, String ToUserName, String CreateTime, HttpServletResponse response, String content){
		StringBuilder buffer = new StringBuilder();
		buffer.append("<xml>\n");
		buffer.append("<ToUserName><![CDATA[").append(FromUserName).append("]]></ToUserName>\n");
		buffer.append("<FromUserName><![CDATA[").append(ToUserName).append("]]></FromUserName>\n");
		buffer.append("<CreateTime><![CDATA[").append(CreateTime).append("]]></CreateTime>\n");
		buffer.append("<MsgType><![CDATA[text]]></MsgType>\n");
		buffer.append("<Content><![CDATA[").append(content).append("]]></Content>\n");
		buffer.append("<FuncFlag>0</FuncFlag>\n");
		buffer.append("</xml>\n");
		sendMessage(response, buffer.toString());
	}

	// 发送信息
	public boolean sendMessage(HttpServletResponse response, String xmlStr) {
		log.info("-----------------------------------------------");
		log.info("method sendMessage start");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml;charset=utf-8");
		try {
			log.info("\n" + xmlStr);
			response.getWriter().println(xmlStr);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
