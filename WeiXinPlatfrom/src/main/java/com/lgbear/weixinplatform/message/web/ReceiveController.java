package com.lgbear.weixinplatform.message.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lgbear.weixinplatform.base.domain.Pagination;
import com.lgbear.weixinplatform.base.web.BaseController;
import com.lgbear.weixinplatform.message.domain.Text;
import com.lgbear.weixinplatform.message.domain.TextCriteria;
import com.lgbear.weixinplatform.message.service.TextService;


@Controller
@RequestMapping("/receive")
public class ReceiveController extends BaseController {
	
	@Resource
	TextService textService;

	@RequestMapping(value = { "/list", "" })
	public String listAll(Model model, @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,  @ModelAttribute(value = "criteria") TextCriteria criteria) {
		String operatorid = "gh_e05a6414704b";
		Pagination<Text> pagination = textService.findByOpeatorId(operatorid, pageIndex);
		model.addAttribute("pagination", pagination);
		model.addAttribute("criteria", criteria);
		return "text/text_list";
//		return "text/advanced_list";
	}

	
	
}
