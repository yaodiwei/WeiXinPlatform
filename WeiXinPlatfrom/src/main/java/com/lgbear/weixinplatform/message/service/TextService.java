package com.lgbear.weixinplatform.message.service;

import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import com.lgbear.weixinplatform.base.domain.Pagination;
import com.lgbear.weixinplatform.message.dao.TextDao;
import com.lgbear.weixinplatform.message.domain.Passive;
import com.lgbear.weixinplatform.message.domain.Text;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Service
public class TextService {
	
	@Resource
	TextDao textDao;
	
	@Resource
	SendService passiveService;
	
	public Pagination<Text> findByOpeatorId(String operatorid, int pageIndex) {
		int totalCount = textDao.countByOpeatorId(operatorid);
		System.out.println(totalCount);
		Pagination<Text> pagination = new Pagination<Text>(totalCount, pageIndex);
		if (totalCount > 0) {
			List<Text> texts = textDao.findByOpeatorId(operatorid, pagination.getSkipResults(), pagination.getEndResults());
			pagination.setResultSet(texts);
			System.out.println(pagination.getSkipResults() + "|" + pagination.getEndResults());
		}
		return pagination;
	}
	
	public void insert(Text text) {
		textDao.insert(text);
	}
	
	
	public void loadTextMessage(Document document, HttpServletResponse response) {
		Element root = document.getRootElement();
		Text text = new Text(root.elementText("MsgId"), root.elementText("Content"), Integer.parseInt(root.elementText("CreateTime")), root.elementText("FromUserName"), root.elementText("ToUserName"));
		textDao.insert(text);
		String reply = findPassiveReply(text);
		sendText(root.elementText("ToUserName"), root.elementText("FromUserName"), System.currentTimeMillis(), reply==""?"您的消息已接受,我们会尽快给予回复.":reply, response);
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

	
	public String findPassiveReply (Text text) {
		List<Passive> passives = passiveService.findEnable(text.getOperatorId());
		String reply = "";
		for (Passive passive : passives) {
			if (Pattern.compile(passive.getName()).matcher(text.getContent()).matches()) {
				if (passive.getValue().contains("&&&")) { 
					StringBuilder replyBuilder = new StringBuilder("");
					String contentAll = passive.getValue().split("&&&")[0];
					String parameterAll = passive.getValue().split("&&&")[1];
					String contents[] = contentAll.split("%%%");
					String parameters[] = parameterAll.split(";");
					for (int i=0; i<parameters.length; i++) {
						int startIndex = Integer.parseInt(parameters[i].split(",")[0]);
						int endIndex = Integer.parseInt(parameters[i].split(",")[1]);
						replyBuilder.append(contents[i]).append(text.getContent().substring(startIndex, endIndex)); 
					}
					replyBuilder.append(contents[contents.length-1]);
					reply = replyBuilder.toString();
				} else {
					reply = passive.getValue();
				}
			}
		}
		return reply;
	}
}
