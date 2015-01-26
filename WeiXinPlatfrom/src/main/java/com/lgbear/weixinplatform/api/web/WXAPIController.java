package com.lgbear.weixinplatform.api.web;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.entity.StringEntity;
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
@RequestMapping("/api")
public class WXAPIController extends BaseController {

	@Resource
	MessageService messageService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String signature(Signature signature) {
		log.info("signature=" + signature);
		String[] temp = new String[] { "yao", signature.getTimestamp(), signature.getNonce() };
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
			log.info("xmlData=" + xmlData);
			Document document = DocumentHelper.parseText(xmlData);
			messageService.loadMessageFormXML(document, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
