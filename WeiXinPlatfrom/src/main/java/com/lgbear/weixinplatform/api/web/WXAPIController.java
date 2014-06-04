package com.lgbear.weixinplatform.api.web;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lgbear.weixinplatform.api.domain.Signature;
import com.lgbear.weixinplatform.api.domain.WeixinMessage;
import com.lgbear.weixinplatform.api.service.MessageService;
import com.lgbear.weixinplatform.api.util.WeixinUtil;
import com.lgbear.weixinplatform.base.util.SHA1;
import com.lgbear.weixinplatform.base.web.BaseController;

@Controller
@RequestMapping("/api/wx")
public class WXAPIController extends BaseController {

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String signature(Signature signature) {
		log.info("signature=" + signature);
		String[] temp = new String[] { "rayUpUpUphahahahha", signature.getTimestamp(), signature.getNonce() };
		log.info("temp=" + temp);
		Arrays.sort(temp);
		String jmh = "";
		for (String temp1 : temp) {
			jmh = jmh + temp1;
		}
		jmh = new SHA1().getDigestOfString(jmh.getBytes());
		log.info("jmh=" + jmh);
		if (jmh.equals(signature.getSignature())) {
			return signature.getEchostr();
		} else {
			return "error";
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public void handle(HttpServletRequest request, HttpServletResponse response) {
		String xmlData = WeixinUtil.loadXmlFromRequest(request);
		try {
			WeixinMessage weixinMessage = generateWeixinMessage(xmlData, response);
			if (weixinMessage != null) {
				log.info("fromUser=" + weixinMessage.getFromUserName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private WeixinMessage generateWeixinMessage(String xmlData, HttpServletResponse response) throws DocumentException {
		log.info("xmlData=" + xmlData);
		Document document = null;
		if (xmlData != null && !xmlData.isEmpty()) {
			document = DocumentHelper.parseText(xmlData);
		} else {
			return null;
		}
		WeixinMessage weixinMessage = new MessageService().loadMessageFormXML(document, response);
		return weixinMessage;
	}
}
