package com.lgbear.weixinplatform.user.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lgbear.weixinplatform.api.util.WeixinUtil;
import com.lgbear.weixinplatform.base.domain.Pagination;
import com.lgbear.weixinplatform.user.dao.UserDao;
import com.lgbear.weixinplatform.user.domain.User;
import com.lgbear.weixinplatform.user.domain.UserCriteria;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

@Service
public class UserService {
	
	@Resource
	UserDao userDao;
	
	public Map<String, Object> getFollowerList() {
		
		Map<String, Object> maps = new HashMap<String, Object>();
		List<String> openIds = null;
		String total = null;
		try {
			// 创建连接
			URL url = new URL("https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + WeixinUtil.getAccess_token() + "&next_openId=");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.connect();

			// 读取响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String lines;
			StringBuffer sb = new StringBuffer("");
			while ((lines = reader.readLine()) != null) {
				lines = new String(lines.getBytes(), "utf-8");
				sb.append(lines);
			}
			
			//读取响应中的json
			JSONObject jsonObject = JSONObject.fromObject(sb.toString());
			JSONObject data = (JSONObject)jsonObject.get("data");
			JSONArray jsonArray = data.getJSONArray("openid");
			openIds = (List)JSONArray.toList(jsonArray);
			total = jsonObject.getString("total");
			
			reader.close();
			// 断开连接
			connection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		maps.put("total", total);
		maps.put("openIds", openIds);
		
		return maps;
	}
	
	@Transactional
	public void getUsersInfo(List<String> openIds) {
		if ("null".equals(openIds.get(0)))
			return;
		List<User> users = new ArrayList<User>();
		for (String openId : openIds) {
			User user = new User();
			try {
				// 创建连接
				URL url = new URL("https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + WeixinUtil.getAccess_token() + "&openid=" + openId + "&lang=zh_CN");
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setRequestMethod("GET");
				connection.setUseCaches(false);
				connection.setInstanceFollowRedirects(true);
				connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				connection.connect();
		
				// 读取响应
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String lines; 
				StringBuffer sb = new StringBuffer("");
				while ((lines = reader.readLine()) != null) {
					lines = new String(lines.getBytes(), "utf-8");
					sb.append(lines);
				}
				
				//读取响应中的json
				JSONObject jsonObject = JSONObject.fromObject(sb.toString());
				if (jsonObject.getInt("subscribe") != 0) {
					user.setOpenId(jsonObject.getString("openid"));
					user.setNickName(jsonObject.getString("nickname"));
					user.setSex(jsonObject.getInt("sex"));
					user.setCity(jsonObject.getString("city"));
					user.setProvince(jsonObject.getString("province"));
					user.setCountry(jsonObject.getString("country"));
					user.setHeadImgUrl(jsonObject.getString("headimgurl"));
					user.setSubscribeTime(jsonObject.getInt("subscribe_time"));
				}
				users.add(user);
				
				reader.close();
				// 断开连接
				connection.disconnect();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		updateUserList(users);
		return;
	}
	
	@Transactional
	public void getUserInfo(String openId) {
		if (StringUtils.isEmpty(openId)) {
			return;
		}
		
		User user = new User();
		try {
			// 创建连接
			URL url = new URL("https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + WeixinUtil.getAccess_token() + "&openid=" + openId + "&lang=zh_CN");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.connect();
	
			// 读取响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String lines; 
			StringBuffer sb = new StringBuffer("");
			while ((lines = reader.readLine()) != null) {
				lines = new String(lines.getBytes(), "utf-8");
				sb.append(lines);
			}
			
			//读取响应中的json
			JSONObject jsonObject = JSONObject.fromObject(sb.toString());
			if (jsonObject.getInt("subscribe") != 0) {
				user.setOpenId(jsonObject.getString("openid"));
				user.setNickName(jsonObject.getString("nickname"));
				user.setSex(jsonObject.getInt("sex"));
				user.setCity(jsonObject.getString("city"));
				user.setProvince(jsonObject.getString("province"));
				user.setCountry(jsonObject.getString("country"));
				user.setHeadImgUrl(jsonObject.getString("headimgurl"));
				user.setSubscribeTime(jsonObject.getInt("subscribe_time"));
			}
			
			reader.close();
			// 断开连接
			connection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		updateUserList(user);
		return;
	}
	
	
	@Transactional
	public void updateUserList(List<User> users) {
		for (User user : users) {
			if (userDao.findOpenId(user.getOpenId()) == null) {
				userDao.insert(user);
			} else {
				userDao.update(user);
			}
		}
//		if (userDao.findByUserCode(user.getUserCode()) != null)
//			throw new IllegalAccessException("User Code (" + user.getUserCode() + ") already exists");
//		userDao.insert(user);
	}
	
	@Transactional
	public void updateUserList(User user) {
		if (userDao.findOpenId(user.getOpenId()) == null) {
			userDao.insert(user);
		} else {
			userDao.update(user);
		}
//		if (userDao.findByUserCode(user.getUserCode()) != null)
//			throw new IllegalAccessException("User Code (" + user.getUserCode() + ") already exists");
//		userDao.insert(user);
	}
	
	
	public Pagination<User> findByCriteria(UserCriteria criteria, int pageIndex) {
		int totalCount = userDao.countByCriteria(criteria);
		Pagination<User> pagination = new Pagination<User>(totalCount, pageIndex);
		if (totalCount > 0) {
			List<User> users = userDao.findByCriteria(criteria, pagination.getSkipResults(), pagination.getPageItem());
			pagination.setResultSet(users);
		}
		return pagination;
	}
	
}
