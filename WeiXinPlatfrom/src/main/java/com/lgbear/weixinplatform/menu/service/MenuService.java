package com.lgbear.weixinplatform.menu.service;


import net.sf.json.JSONObject;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.lgbear.weixinplatform.api.util.WeixinUtil;

@Service
public class MenuService {
	
	
	public void send(@Param("json")String json) {
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + WeixinUtil.getAccess_token();
		WeixinUtil.post(url, json);
	}
	
	public JSONObject get() {
		String url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + WeixinUtil.getAccess_token();
		JSONObject response = WeixinUtil.get(url);
		return response;
	}
	
//	public static void main(String[] args) {
//		List<MenuItem> menu1 = new ArrayList<MenuItem>();
//		menu1.add(new MenuItem("click", "今日歌曲", "V1001_TODAY_MUSIC"));
//		List<MenuItem> menu2 = new ArrayList<MenuItem>();
//		menu2.add(new MenuItem("click", "歌手简介", "V1001_TODAY_SINGER"));
//		List<MenuItem> menu3 = new ArrayList<MenuItem>();
//		menu3.add(new MenuItem("view", "搜索", "http://www.soso.com/"));
//		menu3.add(new MenuItem("view", "视频", "http://v.qq.com/"));
//		menu3.add(new MenuItem("click", "赞一下我们", "V1001_GOOD"));
//		Menu menu = new Menu(null, menu1, null, menu2, "菜单", menu3);
//		send(menu);
//	}
	
}
