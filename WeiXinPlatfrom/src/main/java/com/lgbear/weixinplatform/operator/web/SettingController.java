package com.lgbear.weixinplatform.operator.web;


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

import com.lgbear.weixinplatform.base.web.BaseController;
import com.lgbear.weixinplatform.operator.domain.Operator;
import com.lgbear.weixinplatform.operator.service.OperatorService;
import com.lgbear.weixinplatform.user.domain.User;
import com.lgbear.weixinplatform.user.domain.UserCriteria;
import com.lgbear.weixinplatform.user.service.UserService;


@Controller
@RequestMapping("/setting")
public class SettingController extends BaseController {
	
	@Resource
	OperatorService operatorService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		String loginName = "yao";
		Operator operator = operatorService.find(loginName);
		model.addAttribute("operator", operator);
		return "setting/list";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("operator")Operator operator, Model model) {
		operatorService.update(operator);
		return "setting/list";
	}
	
	

}
