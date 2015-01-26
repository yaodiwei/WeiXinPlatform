package com.lgbear.weixinplatform.message.service;

import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import com.lgbear.weixinplatform.base.domain.Pagination;
import com.lgbear.weixinplatform.message.dao.TextDao;
import com.lgbear.weixinplatform.message.domain.Passive;
import com.lgbear.weixinplatform.message.domain.Text;
import com.lgbear.weixinplatform.user.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Service
public class EventService {
	
	@Resource
	UserService userService;
	
	public void loadEventMessage(Document document, HttpServletResponse response) {
		Element root = document.getRootElement();
//		Text text = new Text(root.elementText("MsgId"), root.elementText("Content"), Integer.parseInt(root.elementText("CreateTime")), root.elementText("FromUserName"), root.elementText("ToUserName"));
		if ("click".equals(root.elementText("Event"))) {
			clickEvent(document, response);
		} else if ("subscribe".equals(root.elementText("Event"))) {
			userService.getUserInfo(root.elementText("FromUserName"));
		}
	}
	
	public void clickEvent (Document document, HttpServletResponse response) {
		Element root = document.getRootElement();
		if ("good_event".equals(root.elementText("EventKey"))) {
			String reply = "";
			sendText(root.elementText("ToUserName"), root.elementText("FromUserName"), System.currentTimeMillis(), reply==""?"收到"+root.elementText("EventKey")+"事件,正在为你准备回应.":reply, response);
		}
	}
	
	
	public void sendText(String FromUserName, String ToUserName, Long CreateTime, String content, HttpServletResponse response){
		StringBuilder buffer = new StringBuilder();
		buffer.append("<xml>\n");
		buffer.append("<ToUserName><![CDATA[").append(ToUserName).append("]]></ToUserName>\n");
		buffer.append("<FromUserName><![CDATA[").append(FromUserName).append("]]></FromUserName>\n");
		buffer.append("<CreateTime><![CDATA[").append(CreateTime).append("]]></CreateTime>\n");
		buffer.append("<MsgType><![CDATA[text]]></MsgType>\n");
		buffer.append("<Content><![CDATA[").append(content).append("]]></Content>\n");
		buffer.append("<FuncFlag>0</FuncFlag>\n");
		buffer.append("</xml>\n");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml;charset=utf-8");
		try {
			response.getWriter().println(buffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
