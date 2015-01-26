package com.lgbear.weixinplatform.message.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lgbear.weixinplatform.base.web.BaseController;
import com.lgbear.weixinplatform.message.domain.Passive;
import com.lgbear.weixinplatform.message.service.SendService;


@Controller
@RequestMapping("/send")
public class SendController extends BaseController {
	
	@Resource
	SendService sendService;

	@RequestMapping("/passive")
	public String passiveList(Model model) {
		String operatorId = "gh_e05a6414704b";
		List<Passive> passives = sendService.findAll(operatorId);
		model.addAttribute("passives", passives);
		return "send/passive_list";
	}
	
	@RequestMapping("/passive/update")
	public String update(@ModelAttribute(value = "passive")Passive passive, Model model) {
		String operatorId = "gh_e05a6414704b";
		passive.setOperatorId(operatorId);
		System.out.println(passive);
		sendService.update(passive);
		return "redirect:/send/passive";
	}
	
	@RequestMapping("/passive/add")
	public String add(@ModelAttribute(value = "passive")Passive passive, Model model) {
		String setOperatorId = "gh_e05a6414704b";
		passive.setOperatorId(setOperatorId);
		sendService.insert(passive);
		return "redirect:/send/passive";
	}
	
	@RequestMapping("/passive/delete")
	public String delete(@RequestParam(value = "name")String name, Model model) {
		String operatorId = "gh_e05a6414704b";
		try {
			name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		sendService.deleteByName(operatorId, name);
		return "redirect:/send/passive";
	}
	
	@RequestMapping("/active")
	public String activeList(Model model) {
		String operatorId = "gh_e05a6414704b";
		model.addAttribute("users", sendService.find48HourUser(operatorId));
		return "/send/active_list";
	}
	
	@RequestMapping("/active/detail/{openId}")
	public String detail(@PathVariable("openId")String openId, Model model) {
		String operatorId = "gh_e05a6414704b";
		model.addAttribute("texts", sendService.findByOpenIdAndOpeatorId(openId, operatorId));
		model.addAttribute("openId", openId);
		return "/send/detail";
	}
	
	@RequestMapping("/active/send")
	public String send(@RequestParam(value = "value")String value, @RequestParam(value = "openId")String openId, Model model) {
		String operatorId = "gh_e05a6414704b";
		sendService.send(value, openId, operatorId);
		return "redirect:/send/active/detail/"+openId;
	}
}
