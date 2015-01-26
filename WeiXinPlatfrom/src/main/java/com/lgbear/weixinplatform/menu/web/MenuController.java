package com.lgbear.weixinplatform.menu.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lgbear.weixinplatform.api.util.WeixinUtil;
import com.lgbear.weixinplatform.base.web.BaseController;
import com.lgbear.weixinplatform.menu.service.MenuService;


@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {
	
	@Resource
	MenuService menuService;

//	@RequestMapping("")
//	public String edit(Model model) {
//		return "menu/edit";
//	}
	
	@RequestMapping("")
	public String get(Model model) {
//		String str = "{\"menu\":{\"button\":[{\"type\":\"click\",\"name\":\"今日歌曲\",\"key\":\"V1001_TODAY_MUSIC\",\"sub_button\":[]},{\"type\":\"click\",\"name\":\"歌手简介\",\"key\":\"V1001_TODAY_SINGER\",\"sub_button\":[]},{\"name\":\"菜单\",\"sub_button\":[{\"type\":\"view\",\"name\":\"搜索\",\"url\":\"http://www.soso.com/\",\"sub_button\":[]},{\"type\":\"view\",\"name\":\"视频\",\"url\":\"http://v.qq.com/\",\"sub_button\":[]},{\"type\":\"click\",\"name\":\"赞一下我们\",\"key\":\"V1001_GOOD\",\"sub_button\":[]}]}]}}";
//		JSONObject response = JSONObject.fromObject(str);
		JSONObject response = menuService.get();
		JSONArray jsonArray = response.getJSONObject("menu").getJSONArray("button");
		
		//1
		JSONObject first = jsonArray.getJSONObject(0);
		if (first.has("type")) {
			model.addAttribute("sel1", -1);
			String menuItems1_type = first.getString("type");model.addAttribute("menuItems1_type", menuItems1_type);
			String menuItems1_name = first.getString("name");model.addAttribute("menuItems1_name", menuItems1_name);
			if ("click".equals(menuItems1_type)) {
				String menuItems1_key = first.getString("key");model.addAttribute("menuItems1_key", menuItems1_key);
			} else {
				String menuItems1_key = first.getString("url");model.addAttribute("menuItems1_key", menuItems1_key);
			}
		} else {
			model.addAttribute("sel1", 1);
			String name1 = first.getString("name");
			JSONArray first_sub = first.getJSONArray("sub_button");
			if (first_sub.size() > 0) {
				String menuItems11_type = first_sub.getJSONObject(0).getString("type");model.addAttribute("menuItems11_type", menuItems11_type);
				String menuItems11_name = first_sub.getJSONObject(0).getString("name");model.addAttribute("menuItems11_name", menuItems11_name);
				if ("click".equals(menuItems11_type)) {
					String menuItems11_key = first_sub.getJSONObject(0).getString("key");model.addAttribute("menuItems11_key", menuItems11_key);
				} else {
					String menuItems11_key = first_sub.getJSONObject(0).getString("url");model.addAttribute("menuItems11_key", menuItems11_key);
				}
			}
			if (first_sub.size() > 1) {
				String menuItems12_type = first_sub.getJSONObject(1).getString("type");model.addAttribute("menuItems12_type", menuItems12_type);
				String menuItems12_name = first_sub.getJSONObject(1).getString("name");model.addAttribute("menuItems12_name", menuItems12_name);
				if ("click".equals(menuItems12_type)) {
					String menuItems12_key = first_sub.getJSONObject(1).getString("key");model.addAttribute("menuItems12_key", menuItems12_key);
				} else {
					String menuItems12_key = first_sub.getJSONObject(1).getString("url");model.addAttribute("menuItems12_key", menuItems12_key);
				}
			}
			if (first_sub.size() > 2) {
				String menuItems13_type = first_sub.getJSONObject(2).getString("type");model.addAttribute("menuItems13_type", menuItems13_type);
				String menuItems13_name = first_sub.getJSONObject(2).getString("name");model.addAttribute("menuItems13_name", menuItems13_name);
				if ("click".equals(menuItems13_type)) {
					String menuItems13_key = first_sub.getJSONObject(2).getString("key");model.addAttribute("menuItems13_key", menuItems13_key);
				} else {
					String menuItems13_key = first_sub.getJSONObject(2).getString("url");model.addAttribute("menuItems13_key", menuItems13_key);
				}
			}
			if (first_sub.size() > 3) {
				String menuItems14_type = first_sub.getJSONObject(3).getString("type");model.addAttribute("menuItems14_type", menuItems14_type);
				String menuItems14_name = first_sub.getJSONObject(3).getString("name");model.addAttribute("menuItems14_name", menuItems14_name);
				if ("click".equals(menuItems14_type)) {
					String menuItems14_key = first_sub.getJSONObject(3).getString("key");model.addAttribute("menuItems14_key", menuItems14_key);
				} else {
					String menuItems14_key = first_sub.getJSONObject(3).getString("url");model.addAttribute("menuItems14_key", menuItems14_key);
				}
			}
			if (first_sub.size() > 4) {
				String menuItems15_type = first_sub.getJSONObject(4).getString("type");model.addAttribute("menuItems15_type", menuItems15_type);
				String menuItems15_name = first_sub.getJSONObject(4).getString("name");model.addAttribute("menuItems15_name", menuItems15_name);
				if ("click".equals(menuItems15_type)) {
					String menuItems15_key = first_sub.getJSONObject(4).getString("key");model.addAttribute("menuItems15_key", menuItems15_key);
				} else {
					String menuItems15_key = first_sub.getJSONObject(4).getString("url");model.addAttribute("menuItems15_key", menuItems15_key);
				}
			}
		}
		
		//2
		JSONObject second = jsonArray.getJSONObject(1);
		if (second.has("type")) {
			model.addAttribute("sel2", -1);
			String menuItems2_type = second.getString("type");model.addAttribute("menuItems2_type", menuItems2_type);
			String menuItems2_name = second.getString("name");model.addAttribute("menuItems2_name", menuItems2_name);
			if ("click".equals(menuItems2_type)) {
				String menuItems2_key = second.getString("key");model.addAttribute("menuItems2_key", menuItems2_key);
			} else {
				String menuItems2_key = second.getString("url");model.addAttribute("menuItems2_key", menuItems2_key);
			}
		} else {
			model.addAttribute("sel2", 1);
			String name2 = second.getString("name");model.addAttribute("name2", name2);
			JSONArray second_sub = second.getJSONArray("sub_button");
			if (second_sub.size() > 0) {
				String menuItems21_type = second_sub.getJSONObject(0).getString("type");model.addAttribute("menuItems21_type", menuItems21_type);
				String menuItems21_name = second_sub.getJSONObject(0).getString("name");model.addAttribute("menuItems21_name", menuItems21_name);
				if ("click".equals(menuItems21_type)) {
					String menuItems21_key = second_sub.getJSONObject(0).getString("key");model.addAttribute("menuItems21_key", menuItems21_key);
				} else {
					String menuItems21_key = second_sub.getJSONObject(0).getString("url");model.addAttribute("menuItems21_key", menuItems21_key);
				}
			}
			if (second_sub.size() > 1) {
				String menuItems22_type = second_sub.getJSONObject(1).getString("type");model.addAttribute("menuItems22_type", menuItems22_type);
				String menuItems22_name = second_sub.getJSONObject(1).getString("name");model.addAttribute("menuItems22_name", menuItems22_name);
				if ("click".equals(menuItems22_type)) {
					String menuItems22_key = second_sub.getJSONObject(1).getString("key");model.addAttribute("menuItems22_key", menuItems22_key);
				} else {
					String menuItems22_key = second_sub.getJSONObject(1).getString("url");model.addAttribute("menuItems22_key", menuItems22_key);
				}
			}
			if (second_sub.size() > 2) {
				String menuItems23_type = second_sub.getJSONObject(2).getString("type");model.addAttribute("menuItems23_type", menuItems23_type);
				String menuItems23_name = second_sub.getJSONObject(2).getString("name");model.addAttribute("menuItems23_name", menuItems23_name);
				if ("click".equals(menuItems23_type)) {
					String menuItems23_key = second_sub.getJSONObject(2).getString("key");model.addAttribute("menuItems23_key", menuItems23_key);
				} else {
					String menuItems23_key = second_sub.getJSONObject(2).getString("url");model.addAttribute("menuItems23_key", menuItems23_key);
				}
			}
			if (second_sub.size() > 3) {
				String menuItems24_type = second_sub.getJSONObject(3).getString("type");model.addAttribute("menuItems24_type", menuItems24_type);
				String menuItems24_name = second_sub.getJSONObject(3).getString("name");model.addAttribute("menuItems24_name", menuItems24_name);
				if ("click".equals(menuItems24_type)) {
					String menuItems24_key = second_sub.getJSONObject(3).getString("key");model.addAttribute("menuItems24_key", menuItems24_key);
				} else {
					String menuItems24_key = second_sub.getJSONObject(3).getString("url");model.addAttribute("menuItems24_key", menuItems24_key);
				}
			}
			if (second_sub.size() > 4) {
				String menuItems25_type = second_sub.getJSONObject(4).getString("type");model.addAttribute("menuItems25_type", menuItems25_type);
				String menuItems25_name = second_sub.getJSONObject(4).getString("name");model.addAttribute("menuItems25_name", menuItems25_name);
				if ("click".equals(menuItems25_type)) {
					String menuItems25_key = second_sub.getJSONObject(4).getString("key");model.addAttribute("menuItems25_key", menuItems25_key);
				} else {
					String menuItems25_key = second_sub.getJSONObject(4).getString("url");model.addAttribute("menuItems25_key", menuItems25_key);
				}
			}
		}
		
		//3
		JSONObject third = jsonArray.getJSONObject(2);
		if (third.has("type")) {
			model.addAttribute("sel3", -1);
			String menuItems3_type = third.getString("type");model.addAttribute("menuItems3_type", menuItems3_type);
			String menuItems3_name = third.getString("name");model.addAttribute("menuItems3_name", menuItems3_name);
			if ("click".equals(menuItems3_type)) {
				String menuItems3_key = third.getString("key");model.addAttribute("menuItems3_key", menuItems3_key);
			} else {
				String menuItems3_key = third.getString("url");model.addAttribute("menuItems3_key", menuItems3_key);
			}
		} else {
			model.addAttribute("sel3", 1);
			String name3 = third.getString("name");model.addAttribute("name3", name3);
			JSONArray third_sub = third.getJSONArray("sub_button");
			if (third_sub.size() > 0) {
				String menuItems31_type = third_sub.getJSONObject(0).getString("type");model.addAttribute("menuItems31_type", menuItems31_type);
				String menuItems31_name = third_sub.getJSONObject(0).getString("name");model.addAttribute("menuItems31_name", menuItems31_name);
				if ("click".equals(menuItems31_type)) {
					String menuItems31_key = third_sub.getJSONObject(0).getString("key");model.addAttribute("menuItems31_key", menuItems31_key);
				} else {
					String menuItems31_key = third_sub.getJSONObject(0).getString("url");model.addAttribute("menuItems31_key", menuItems31_key);
				}
			}
			if (third_sub.size() > 1) {
				String menuItems32_type = third_sub.getJSONObject(1).getString("type");model.addAttribute("menuItems32_type", menuItems32_type);
				String menuItems32_name = third_sub.getJSONObject(1).getString("name");model.addAttribute("menuItems32_name", menuItems32_name);
				if ("click".equals(menuItems32_type)) {
					String menuItems32_key = third_sub.getJSONObject(1).getString("key");model.addAttribute("menuItems32_key", menuItems32_key);
				} else {
					String menuItems32_key = third_sub.getJSONObject(1).getString("url");model.addAttribute("menuItems32_key", menuItems32_key);
				}
			}
			if (third_sub.size() > 2) {
				String menuItems33_type = third_sub.getJSONObject(2).getString("type");model.addAttribute("menuItems33_type", menuItems33_type);
				String menuItems33_name = third_sub.getJSONObject(2).getString("name");model.addAttribute("menuItems33_name", menuItems33_name);
				if ("click".equals(menuItems33_type)) {
					String menuItems33_key = third_sub.getJSONObject(2).getString("key");model.addAttribute("menuItems33_key", menuItems33_key);
				} else {
					String menuItems33_key = third_sub.getJSONObject(2).getString("url");model.addAttribute("menuItems33_key", menuItems33_key);
				}
			}
			if (third_sub.size() > 3) {
				String menuItems34_type = third_sub.getJSONObject(3).getString("type");model.addAttribute("menuItems34_type", menuItems34_type);
				String menuItems34_name = third_sub.getJSONObject(3).getString("name");model.addAttribute("menuItems34_name", menuItems34_name);
				if ("click".equals(menuItems34_type)) {
					String menuItems34_key = third_sub.getJSONObject(3).getString("key");model.addAttribute("menuItems34_key", menuItems34_key);
				} else {
					String menuItems34_key = third_sub.getJSONObject(3).getString("url");model.addAttribute("menuItems34_key", menuItems34_key);
				}
			}
			if (third_sub.size() > 4) {
				String menuItems35_type = third_sub.getJSONObject(4).getString("type");model.addAttribute("menuItems35_type", menuItems35_type);
				String menuItems35_name = third_sub.getJSONObject(4).getString("name");model.addAttribute("menuItems35_name", menuItems35_name);
				if ("click".equals(menuItems35_type)) {
					String menuItems35_key = third_sub.getJSONObject(4).getString("key");model.addAttribute("menuItems35_key", menuItems35_key);
				} else {
					String menuItems35_key = third_sub.getJSONObject(4).getString("url");model.addAttribute("menuItems35_key", menuItems35_key);
				}
			}
		}
		
		
		return "menu/edit";
	}
	
	public static void main(String[] args) {
		String str = "{\"menu\":{\"button\":[{\"type\":\"click\",\"name\":\"今日歌曲\",\"key\":\"V1001_TODAY_MUSIC\",\"sub_button\":[]},{\"type\":\"click\",\"name\":\"歌手简介\",\"key\":\"V1001_TODAY_SINGER\",\"sub_button\":[]},{\"name\":\"菜单\",\"sub_button\":[{\"type\":\"view\",\"name\":\"搜索\",\"url\":\"http://www.soso.com/\",\"sub_button\":[]},{\"type\":\"view\",\"name\":\"视频\",\"url\":\"http://v.qq.com/\",\"sub_button\":[]},{\"type\":\"click\",\"name\":\"赞一下我们\",\"key\":\"V1001_GOOD\",\"sub_button\":[]}]}]}}";
		JSONObject response = JSONObject.fromObject(str);
		JSONArray jsonArray = response.getJSONObject("menu").getJSONArray("button");
		
		System.out.println(jsonArray.toString());
		//1
		JSONObject first = jsonArray.getJSONObject(0);
		System.out.println(first);
		System.out.println(first.has("type"));
		if (first.has("type")) {
			String menuItems1_type = first.getString("type");
			String menuItems1_name = first.getString("name");
			if ("click".equals(menuItems1_type)) {
				String menuItems1_key = first.getString("key");
			} else {
				String menuItems1_key = first.getString("url");
			}
		} else {
			String name1 = first.getString("name");
			JSONArray first_sub = first.getJSONArray("sub_button");
			if (first_sub.size() > 0) {
				String menuItems11_type = first_sub.getJSONObject(0).getString("type");
				String menuItems11_name = first_sub.getJSONObject(0).getString("name");
				if ("click".equals(menuItems11_type)) {
					String menuItems11_key = first_sub.getJSONObject(0).getString("key");
				} else {
					String menuItems11_key = first_sub.getJSONObject(0).getString("url");
				}
			}
			if (first_sub.size() > 1) {
				String menuItems12_type = first_sub.getJSONObject(1).getString("type");
				String menuItems12_name = first_sub.getJSONObject(1).getString("name");
				if ("click".equals(menuItems12_type)) {
					String menuItems12_key = first_sub.getJSONObject(1).getString("key");
				} else {
					String menuItems12_key = first_sub.getJSONObject(1).getString("url");
				}
			}
			if (first_sub.size() > 2) {
				String menuItems13_type = first_sub.getJSONObject(2).getString("type");
				String menuItems13_name = first_sub.getJSONObject(2).getString("name");
				if ("click".equals(menuItems13_type)) {
					String menuItems13_key = first_sub.getJSONObject(2).getString("key");
				} else {
					String menuItems13_key = first_sub.getJSONObject(2).getString("url");
				}
			}
			if (first_sub.size() > 3) {
				String menuItems14_type = first_sub.getJSONObject(3).getString("type");
				String menuItems14_name = first_sub.getJSONObject(3).getString("name");
				if ("click".equals(menuItems14_type)) {
					String menuItems14_key = first_sub.getJSONObject(3).getString("key");
				} else {
					String menuItems14_key = first_sub.getJSONObject(3).getString("url");
				}
			}
			if (first_sub.size() > 4) {
				String menuItems15_type = first_sub.getJSONObject(4).getString("type");
				String menuItems15_name = first_sub.getJSONObject(4).getString("name");
				if ("click".equals(menuItems15_type)) {
					String menuItems15_key = first_sub.getJSONObject(4).getString("key");
				} else {
					String menuItems15_key = first_sub.getJSONObject(4).getString("url");
				}
			}
		}
		
		//2
		JSONObject second = jsonArray.getJSONObject(1);
		System.out.println(second);
		System.out.println(second.has("type"));
		if (second.has("type")) {
			String menuItems2_type = second.getString("type");
			String menuItems2_name = second.getString("name");
			if ("click".equals(menuItems2_type)) {
				String menuItems2_key = second.getString("key");
			} else {
				String menuItems2_key = second.getString("url");
			}
		} else {
			String name1 = second.getString("name");
			JSONArray second_sub = second.getJSONArray("sub_button");
			if (second_sub.size() > 0) {
				String menuItems21_type = second_sub.getJSONObject(0).getString("type");
				String menuItems21_name = second_sub.getJSONObject(0).getString("name");
				if ("click".equals(menuItems21_type)) {
					String menuItems21_key = second_sub.getJSONObject(0).getString("key");
				} else {
					String menuItems21_key = second_sub.getJSONObject(0).getString("url");
				}
			}
			if (second_sub.size() > 1) {
				String menuItems22_type = second_sub.getJSONObject(1).getString("type");
				String menuItems22_name = second_sub.getJSONObject(1).getString("name");
				if ("click".equals(menuItems22_type)) {
					String menuItems22_key = second_sub.getJSONObject(1).getString("key");
				} else {
					String menuItems22_key = second_sub.getJSONObject(1).getString("url");
				}
			}
			if (second_sub.size() > 2) {
				String menuItems23_type = second_sub.getJSONObject(2).getString("type");
				String menuItems23_name = second_sub.getJSONObject(2).getString("name");
				if ("click".equals(menuItems23_type)) {
					String menuItems23_key = second_sub.getJSONObject(2).getString("key");
				} else {
					String menuItems23_key = second_sub.getJSONObject(2).getString("url");
				}
			}
			if (second_sub.size() > 3) {
				String menuItems24_type = second_sub.getJSONObject(3).getString("type");
				String menuItems24_name = second_sub.getJSONObject(3).getString("name");
				if ("click".equals(menuItems24_type)) {
					String menuItems24_key = second_sub.getJSONObject(3).getString("key");
				} else {
					String menuItems24_key = second_sub.getJSONObject(3).getString("url");
				}
			}
			if (second_sub.size() > 4) {
				String menuItems25_type = second_sub.getJSONObject(4).getString("type");
				String menuItems25_name = second_sub.getJSONObject(4).getString("name");
				if ("click".equals(menuItems25_type)) {
					String menuItems25_key = second_sub.getJSONObject(4).getString("key");
				} else {
					String menuItems25_key = second_sub.getJSONObject(4).getString("url");
				}
			}
		}
		
		//3
		JSONObject third = jsonArray.getJSONObject(2);
		System.out.println(third);
		System.out.println(third.has("type"));
		if (third.has("type")) {
			String menuItems3_type = third.getString("type");
			String menuItems3_name = third.getString("name");
			if ("click".equals(menuItems3_type)) {
				String menuItems3_key = third.getString("key");
			} else {
				String menuItems3_key = third.getString("url");
			}
		} else {
			String name1 = third.getString("name");
			JSONArray third_sub = third.getJSONArray("sub_button");
			if (third_sub.size() > 0) {
				String menuItems31_type = third_sub.getJSONObject(0).getString("type");
				String menuItems31_name = third_sub.getJSONObject(0).getString("name");
				if ("click".equals(menuItems31_type)) {
					String menuItems31_key = third_sub.getJSONObject(0).getString("key");
				} else {
					String menuItems31_key = third_sub.getJSONObject(0).getString("url");
				}
			}
			if (third_sub.size() > 1) {
				String menuItems32_type = third_sub.getJSONObject(1).getString("type");
				String menuItems32_name = third_sub.getJSONObject(1).getString("name");
				if ("click".equals(menuItems32_type)) {
					String menuItems32_key = third_sub.getJSONObject(1).getString("key");
				} else {
					String menuItems32_key = third_sub.getJSONObject(1).getString("url");
				}
			}
			if (third_sub.size() > 2) {
				String menuItems33_type = third_sub.getJSONObject(2).getString("type");
				String menuItems33_name = third_sub.getJSONObject(2).getString("name");
				if ("click".equals(menuItems33_type)) {
					String menuItems33_key = third_sub.getJSONObject(2).getString("key");
				} else {
					String menuItems33_key = third_sub.getJSONObject(2).getString("url");
				}
			}
			if (third_sub.size() > 3) {
				String menuItems34_type = third_sub.getJSONObject(3).getString("type");
				String menuItems34_name = third_sub.getJSONObject(3).getString("name");
				if ("click".equals(menuItems34_type)) {
					String menuItems34_key = third_sub.getJSONObject(3).getString("key");
				} else {
					String menuItems34_key = third_sub.getJSONObject(3).getString("url");
				}
			}
			if (third_sub.size() > 4) {
				String menuItems35_type = third_sub.getJSONObject(4).getString("type");
				String menuItems35_name = third_sub.getJSONObject(4).getString("name");
				if ("click".equals(menuItems35_type)) {
					String menuItems35_key = third_sub.getJSONObject(4).getString("key");
				} else {
					String menuItems35_key = third_sub.getJSONObject(4).getString("url");
				}
			}
		}
		
		
		
		
		
	}
	
	@RequestMapping("/update")
	public String update(
			int sel1, String name1, 
			String menuItems11_type, String menuItems11_name, String menuItems11_key,
			String menuItems12_type, String menuItems12_name, String menuItems12_key,
			String menuItems13_type, String menuItems13_name, String menuItems13_key,
			String menuItems14_type, String menuItems14_name, String menuItems14_key,
			String menuItems15_type, String menuItems15_name, String menuItems15_key,
			String menuItems1_type, String menuItems1_name, String menuItems1_key,
			int sel2, String name2, 
			String menuItems21_type, String menuItems21_name, String menuItems21_key,
			String menuItems22_type, String menuItems22_name, String menuItems22_key,
			String menuItems23_type, String menuItems23_name, String menuItems23_key,
			String menuItems24_type, String menuItems24_name, String menuItems24_key,
			String menuItems25_type, String menuItems25_name, String menuItems25_key,
			String menuItems2_type, String menuItems2_name, String menuItems2_key,
			int sel3, String name3, 
			String menuItems31_type, String menuItems31_name, String menuItems31_key,
			String menuItems32_type, String menuItems32_name, String menuItems32_key,
			String menuItems33_type, String menuItems33_name, String menuItems33_key,
			String menuItems34_type, String menuItems34_name, String menuItems34_key,
			String menuItems35_type, String menuItems35_name, String menuItems35_key,
			String menuItems3_type, String menuItems3_name, String menuItems3_key,
			Model model) {
//		System.out.println(
//				sel1 +"---"+ name1 
//				+"---"+ menuItems11_type +"---"+ menuItems11_name +"---"+ menuItems11_key
//				+"---"+ menuItems12_type +"---"+ menuItems12_name +"---"+ menuItems12_key
//				+"---"+ menuItems13_type +"---"+ menuItems13_name +"---"+ menuItems13_key
//				+"---"+ menuItems14_type +"---"+ menuItems14_name +"---"+ menuItems14_key
//				+"---"+ menuItems15_type +"---"+ menuItems15_name +"---"+ menuItems15_key
//				+"---"+ menuItems1_type +"---"+ menuItems1_name +"---"+ menuItems1_key
//				+"---"+ sel2 +"---"+ name2 
//				+"---"+ menuItems21_type +"---"+ menuItems21_name +"---"+ menuItems21_key
//				+"---"+ menuItems22_type +"---"+ menuItems22_name +"---"+ menuItems22_key
//				+"---"+ menuItems23_type +"---"+ menuItems23_name +"---"+ menuItems23_key
//				+"---"+ menuItems24_type +"---"+ menuItems24_name +"---"+ menuItems24_key
//				+"---"+ menuItems25_type +"---"+ menuItems25_name +"---"+ menuItems25_key
//				+"---"+ menuItems2_type +"---"+ menuItems2_name +"---"+ menuItems2_key
//				+"---"+ sel3 +"---"+ name3 
//				+"---"+ menuItems31_type +"---"+ menuItems31_name +"---"+ menuItems31_key
//				+"---"+ menuItems32_type +"---"+ menuItems32_name +"---"+ menuItems32_key
//				+"---"+ menuItems33_type +"---"+ menuItems33_name +"---"+ menuItems33_key
//				+"---"+ menuItems34_type +"---"+ menuItems34_name +"---"+ menuItems34_key
//				+"---"+ menuItems35_type +"---"+ menuItems35_name +"---"+ menuItems35_key
//				+"---"+ menuItems3_type +"---"+ menuItems3_name +"---"+ menuItems3_key
//				);
		
//		{
//		     "button":[
//		     {	
//		          "type":"click",
//		          "name":"今日歌曲",
//		          "key":"V1001_TODAY_MUSIC"
//		      },
//		      {
//		           "type":"click",
//		           "name":"歌手简介",
//		           "key":"V1001_TODAY_SINGER"
//		      },
//		      {
//		           "name":"菜单",
//		           "sub_button":[
//		           {	
//		               "type":"view",
//		               "name":"搜索",
//		               "url":"http://www.soso.com/"
//		            },
//		            {
//		               "type":"view",
//		               "name":"视频",
//		               "url":"http://v.qq.com/"
//		            },
//		            {
//		               "type":"click",
//		               "name":"赞一下我们",
//		               "key":"V1001_GOOD"
//		            }]
//		       }]
//		 }
		
		StringBuilder json = new StringBuilder("{\"button\":[{");
		if (sel1 == -1) {
			json.append("\"type\":\"" + menuItems1_type + "\",").append("\"name\":\"" + menuItems1_name + "\",");
			if ("click".equals(menuItems1_type)) {
				json.append("\"key\":\"" + menuItems1_key + "\"");
			} else {
				json.append("\"url\":\"" + menuItems1_key + "\"");
			}
			json.append("}");
		} else if (sel1 == 1){
			json.append("\"name\":\"" + name1 + "\",\"sub_button\":[{");
			if (menuItems11_name.length() != 0 && menuItems11_key.length() != 0) {
				json.append("\"type\":\"" + menuItems11_type + "\",").append("\"name\":\"" + menuItems11_name + "\",");
				if ("click".equals(menuItems11_type)) {
					json.append("\"key\":\"" + menuItems11_key + "\"");
				} else {
					json.append("\"url\":\"" + menuItems11_key + "\"");
				}
				json.append("}");
			}
			if (menuItems12_name.length() != 0 && menuItems12_key.length() != 0) {
				json.append(",{");
				json.append("\"type\":\"" + menuItems12_type + "\",").append("\"name\":\"" + menuItems12_name + "\",");
				if ("click".equals(menuItems12_type)) {
					json.append("\"key\":\"" + menuItems12_key + "\"");
				} else {
					json.append("\"url\":\"" + menuItems12_key + "\"");
				}
				json.append("}");
			}
			if (menuItems13_name.length() != 0 && menuItems13_key.length() != 0) {
				json.append(",{");
				json.append("\"type\":\"" + menuItems13_type + "\",").append("\"name\":\"" + menuItems13_name + "\",");
				if ("click".equals(menuItems13_type)) {
					json.append("\"key\":\"" + menuItems13_key + "\"");
				} else {
					json.append("\"url\":\"" + menuItems13_key + "\"");
				}
				json.append("}");
			}
			if (menuItems14_name.length() != 0 && menuItems14_key.length() != 0) {
				json.append(",{");
				json.append("\"type\":\"" + menuItems14_type + "\",").append("\"name\":\"" + menuItems14_name + "\",");
				if ("click".equals(menuItems14_type)) {
					json.append("\"key\":\"" + menuItems14_key + "\"");
				} else {
					json.append("\"url\":\"" + menuItems14_key + "\"");
				}
				json.append("}");
			}
			if (menuItems15_name.length() != 0 && menuItems15_key.length() != 0) {
				json.append(",{");
				json.append("\"type\":\"" + menuItems15_type + "\",").append("\"name\":\"" + menuItems15_name + "\",");
				if ("click".equals(menuItems15_type)) {
					json.append("\"key\":\"" + menuItems15_key + "\"");
				} else {
					json.append("\"url\":\"" + menuItems15_key + "\"");
				}
				json.append("}");
			}
			json.append("]}");
		}
		
		if (sel2 == -1) {
			json.append(",{");
			json.append("\"type\":\"" + menuItems2_type + "\",").append("\"name\":\"" + menuItems2_name + "\",");
			if ("click".equals(menuItems2_type)) {
				json.append("\"key\":\"" + menuItems2_key + "\"");
			} else {
				json.append("\"url\":\"" + menuItems2_key + "\"");
			}
			json.append("}");
		} else if (sel2 == 1){
			json.append(",{");
			json.append("\"name\":\"" + name2 + "\",\"sub_button\":[{");
			if (menuItems21_name.length() != 0 && menuItems21_key.length() != 0) {
				json.append("\"type\":\"" + menuItems21_type + "\",").append("\"name\":\"" + menuItems21_name + "\",");
				if ("click".equals(menuItems21_type)) {
					json.append("\"key\":\"" + menuItems21_key + "\"");
				} else {
					json.append("\"url\":\"" + menuItems21_key + "\"");
				}
				json.append("}");
			}
			if (menuItems22_name.length() != 0 && menuItems22_key.length() != 0) {
				json.append(",{");
				json.append("\"type\":\"" + menuItems22_type + "\",").append("\"name\":\"" + menuItems22_name + "\",");
				if ("click".equals(menuItems22_type)) {
					json.append("\"key\":\"" + menuItems22_key + "\"");
				} else {
					json.append("\"url\":\"" + menuItems22_key + "\"");
				}
				json.append("}");
			}
			if (menuItems23_name.length() != 0 && menuItems23_key.length() != 0) {
				json.append(",{");
				json.append("\"type\":\"" + menuItems23_type + "\",").append("\"name\":\"" + menuItems23_name + "\",");
				if ("click".equals(menuItems23_type)) {
					json.append("\"key\":\"" + menuItems23_key + "\"");
				} else {
					json.append("\"url\":\"" + menuItems23_key + "\"");
				}
				json.append("}");
			}
			if (menuItems24_name.length() != 0 && menuItems24_key.length() != 0) {
				json.append(",{");
				json.append("\"type\":\"" + menuItems24_type + "\",").append("\"name\":\"" + menuItems24_name + "\",");
				if ("click".equals(menuItems24_type)) {
					json.append("\"key\":\"" + menuItems24_key + "\"");
				} else {
					json.append("\"url\":\"" + menuItems24_key + "\"");
				}
				json.append("}");
			}
			if (menuItems25_name.length() != 0 && menuItems25_key.length() != 0) {
				json.append(",{");
				json.append("\"type\":\"" + menuItems25_type + "\",").append("\"name\":\"" + menuItems25_name + "\",");
				if ("click".equals(menuItems25_type)) {
					json.append("\"key\":\"" + menuItems25_key + "\"");
				} else {
					json.append("\"url\":\"" + menuItems25_key + "\"");
				}
				json.append("}");
			}
			json.append("]}");
		}
		
		
		if (sel3 == -1) {
			json.append(",{");
			json.append("\"type\":\"" + menuItems3_type + "\",").append("\"name\":\"" + menuItems3_name + "\",");
			if ("click".equals(menuItems3_type)) {
				json.append("\"key\":\"" + menuItems3_key + "\"");
			} else {
				json.append("\"url\":\"" + menuItems3_key + "\"");
			}
			json.append("}");
		} else if (sel3 == 1){
			json.append(",{");
			json.append("\"name\":\"" + name3 + "\",\"sub_button\":[{");
			if (menuItems31_name.length() != 0 && menuItems31_key.length() != 0) {
				json.append("\"type\":\"" + menuItems31_type + "\",").append("\"name\":\"" + menuItems31_name + "\",");
				if ("click".equals(menuItems31_type)) {
					json.append("\"key\":\"" + menuItems31_key + "\"");
				} else {
					json.append("\"url\":\"" + menuItems31_key + "\"");
				}
				json.append("}");
			}
			if (menuItems32_name.length() != 0 && menuItems32_key.length() != 0) {
				json.append(",{");
				json.append("\"type\":\"" + menuItems32_type + "\",").append("\"name\":\"" + menuItems32_name + "\",");
				if ("click".equals(menuItems32_type)) {
					json.append("\"key\":\"" + menuItems32_key + "\"");
				} else {
					json.append("\"url\":\"" + menuItems32_key + "\"");
				}
				json.append("}");
			}
			if (menuItems33_name.length() != 0 && menuItems33_key.length() != 0) {
				json.append(",{");
				json.append("\"type\":\"" + menuItems33_type + "\",").append("\"name\":\"" + menuItems33_name + "\",");
				if ("click".equals(menuItems33_type)) {
					json.append("\"key\":\"" + menuItems33_key + "\"");
				} else {
					json.append("\"url\":\"" + menuItems33_key + "\"");
				}
				json.append("}");
			}
			if (menuItems34_name.length() != 0 && menuItems34_key.length() != 0) {
				json.append(",{");
				json.append("\"type\":\"" + menuItems34_type + "\",").append("\"name\":\"" + menuItems34_name + "\",");
				if ("click".equals(menuItems34_type)) {
					json.append("\"key\":\"" + menuItems34_key + "\"");
				} else {
					json.append("\"url\":\"" + menuItems34_key + "\"");
				}
				json.append("}");
			}
			if (menuItems35_name.length() != 0 && menuItems35_key.length() != 0) {
				json.append(",{");
				json.append("\"type\":\"" + menuItems35_type + "\",").append("\"name\":\"" + menuItems35_name + "\",");
				if ("click".equals(menuItems35_type)) {
					json.append("\"key\":\"" + menuItems35_key + "\"");
				} else {
					json.append("\"url\":\"" + menuItems35_key + "\"");
				}
				json.append("}");
			}
			json.append("]}");
		}
		json.append("]}");
		menuService.send(json.toString());
		return "redirect:/menu";
	}
	
	
}
