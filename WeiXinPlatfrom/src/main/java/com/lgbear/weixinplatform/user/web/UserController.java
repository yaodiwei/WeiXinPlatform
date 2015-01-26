package com.lgbear.weixinplatform.user.web;


import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lgbear.weixinplatform.base.domain.Pagination;
import com.lgbear.weixinplatform.base.web.BaseController;
import com.lgbear.weixinplatform.user.domain.User;
import com.lgbear.weixinplatform.user.domain.UserCriteria;
import com.lgbear.weixinplatform.user.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Resource
	UserService userService;

	@RequestMapping(value="/weixinlist", method = RequestMethod.GET)
	public String weixinlist(Model model) {
		Map<String, Object> maps = userService.getFollowerList();
		model.addAttribute("openIds", maps.get("openIds"));
		model.addAttribute("total", maps.get("total"));
		return "user/weixin_list";
	}
	
	@RequestMapping(value="/list")
	public String list(@ModelAttribute("criteria") UserCriteria criteria, @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex, Model model ) {
		criteria.setLike(true);
		Pagination<User> pagination = userService.findByCriteria(criteria, pageIndex);
		model.addAttribute("pagination", pagination);
		return "user/list";
		}
	
	
	@RequestMapping(value="/getUsersInfo", method = RequestMethod.POST)
	public String getUsersInfo (@RequestParam(value="openIds", required = true, defaultValue="null") List<String> openIds, Model model) {
		userService.getUsersInfo(openIds);
		return "redirect:/user/list";
	}

//	@RequestMapping("/add")
//	public String add(Model model) {
//		model.addAttribute("competitions", competitionService.findAll());
//		return "user/user_add";
//	}
//
//	@RequestMapping(value="/checkUserIsExist", method = RequestMethod.GET)
//	public void checkUserIsExist(@RequestParam(value="userCode") String userCode,HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
//		try {
//			userService.checkUserIsExist(userCode);
//			response.getWriter().write("the userCode is available");
//			response.getWriter().flush(); 
//	        response.getWriter().close(); 
//		} catch (Exception e) {
//			model.addAttribute("result", e.getMessage());
//	        response.getWriter().write(e.getMessage()); 
//	        response.getWriter().flush(); 
//	        response.getWriter().close(); 
//		}
//	}
//	
//	@RequestMapping(method = RequestMethod.POST)
//	public String insert(@ModelAttribute("user") User user, @RequestParam(value="competitions", required = false, defaultValue="-99") List<Integer> competitions, @RequestParam(value="seasonStrings", required = false, defaultValue="null") List<String> seasonStrings, Model model) {
//		try {
//			user.setPassword(userService.encryptPassword(user.getPassword()));
//			userService.insert(user, seasonStrings);
//			return "redirect:/user";
//		} catch (Exception e) {
//			user.setPassword("");
//			model.addAttribute("result", e.getMessage());
//			e.printStackTrace();
//			return add(model);
//		}
//	}
//
//	
//	@RequestMapping("/{userCode}")
//	public String edit(@PathVariable("userCode") String userCode, Model model) {
//		User user = userService.findByUserCode(userCode);
//		//检验user是否过期.如果过期并且为Active,就更改他的状态为Expired.
//		if (user.getExpiryDate().before(new Date())) {
//			if(user.getStatus()==1){
//				user.setStatus(2);
//				userDao.update(user);
//			} else if (user.getStatus()==0) {
//				user.setStatus(4);
//				userDao.update(user);
//			}
//		}
//		//检查完毕
//		user.setPassword(userService.decryptPassword(user.getPassword()));
//		model.addAttribute("competitions", competitionService.findAll());
//		model.addAttribute("user", user);
//		model.addAttribute("userCompetitions",subscriptionDao.findCompetitionsByUserCode(userCode));
//		model.addAttribute("userSeasons",subscriptionDao.findSeasonsByUserCode(userCode));
//		model.addAttribute("subscriptions",subscriptionService.findByUserCode(userCode));
//		return "user/user_edit";
//	}
//
//	@RequestMapping(method = RequestMethod.PUT)
//	public String update(@ModelAttribute("user") User user, @RequestParam(value="competitions", required = false, defaultValue="-99") List<Integer> competitions, @RequestParam(value="seasonStrings", required = false, defaultValue="null") List<String> seasonStrings, Model model) {
//		user.setPassword(userService.encryptPassword(user.getPassword()));
//		userService.update(user, seasonStrings);
//		model.addAttribute("user", user);
//		model.addAttribute("result", "successful");
//		return "redirect:/user";
//	}
//
//	@RequestMapping("/changePassword")
//	public String changePassword(@RequestParam("userCode") String userCode, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, Model model) {
//		User user = userService.findByUserCode(userCode);
//		model.addAttribute("user", user);
//		if("@PasS@".equalsIgnoreCase(oldPassword) || user.getPassword().equals(userService.encryptPassword(oldPassword))){
//			user.setPassword(userService.encryptPassword(newPassword));
//			userService.update(user);
//			model.addAttribute("result", "successful");
//			return "user/user_edit";
//		}else {
//			model.addAttribute("result", "The old password you gave is incorrect.");
//			return "user/user_edit";
//		}
//	}
//	
//	@RequestMapping("/delete")
//	public String deleteUser(@RequestParam("userCode") String userCode) {
//		subscriptionDao.deleteByUserCode(userCode);
//		userService.deleteByUserCode(userCode);
//		return "redirect:/user";
//	}

}
