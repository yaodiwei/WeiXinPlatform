package com.lgbear.weixinplatform.message.service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lgbear.weixinplatform.api.util.WeixinUtil;
import com.lgbear.weixinplatform.message.dao.SendDao;
import com.lgbear.weixinplatform.message.dao.TextDao;
import com.lgbear.weixinplatform.message.domain.Passive;
import com.lgbear.weixinplatform.message.domain.Text;
import com.lgbear.weixinplatform.user.dao.UserDao;
import com.lgbear.weixinplatform.user.domain.User;

@Service
public class SendService {
	
	@Resource
	private CacheManager cacheManager;
	
	@Resource
	SendDao sendDao;
	
	@Resource
	TextDao textDao;
	
	@Resource
	UserDao userDao;
	
	@Transactional
	public void insert(Passive passive) {
		sendDao.insert(passive);
		Cache cache = cacheManager.getCache("passives");
		cache.clear();
	}
	
	@Transactional
	public List<Passive> findAll(String operatorId) {
		return sendDao.findAll(operatorId);
	}
	
	@Transactional
	@Cacheable(value="passives",key="#operatorId")
	public List<Passive> findEnable(String operatorId) {
		return sendDao.findEnable(operatorId);
	}
	
	@Transactional
	public void update(Passive passive) {
		sendDao.update(passive);
		Cache cache = cacheManager.getCache("passives");
		cache.clear();
	}
	
	@Transactional
	public void deleteByName(@Param("operatorId") String operatorId, @Param("name") String name) {
		sendDao.deleteByName(operatorId, name);
		Cache cache = cacheManager.getCache("passives");
		cache.clear();
	}
	
	@Transactional
	public void send(@Param("value") String value, @Param("openId") String openId, @Param("operatorId") String operatorId) {
		textDao.insert(new Text(String.valueOf(System.currentTimeMillis()), value, (int) (System.currentTimeMillis()/1000L), openId, operatorId));
		JSONObject jsonObjectText = new JSONObject();
		jsonObjectText.put("content", value);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("touser", openId);
		jsonObject.put("msgtype", "text");
		jsonObject.put("text", jsonObjectText);
		String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + WeixinUtil.getAccess_token();
		WeixinUtil.post(url, jsonObject);
	}
	
	
	
	public Boolean checkUserLastMsgTime(String openId, String operatorId) {
		int createTime = textDao.findLastMsgTimeByOpenId(openId, operatorId);
		int currentTime = (int) System.currentTimeMillis()/1000;
		if (currentTime - createTime > 172800) {
			return true;
		}
		return false;
    }
	
	public List<User> find48HourUser(String operatorId) {
		int currentTime = (int) (System.currentTimeMillis()/1000);
		List<User> users = userDao.find48HourUser(currentTime, operatorId);
		System.out.println(users);
		return users;
    }
	
	public List<Text> findByOpenIdAndOpeatorId(String openId, String operatorId) {
		return textDao.findByOpenIdAndOpeatorId(openId, operatorId);
    }
}
