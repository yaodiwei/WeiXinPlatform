package com.lgbear.weixinplatform.base.web;

import java.util.Date;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.lgbear.weixinplatform.base.typeeditor.DateTypeEditor;
import com.lgbear.weixinplatform.base.typeeditor.DoubleTypeEditor;
import com.lgbear.weixinplatform.base.typeeditor.FloatTypeEditor;
import com.lgbear.weixinplatform.base.typeeditor.IntTypeEditor;
import com.lgbear.weixinplatform.base.typeeditor.LongTypeEditor;

public class BaseController {

	protected Logger log = LoggerFactory.getLogger(getClass());

	protected ServletContext servletContext;
	
	protected static final String DATE_DEFAULT_PARSE_PATTERN = "yyyy-MM-dd";
	protected static final String DATETIME_DEFAULT_PARSE_PATTERN = "yyyy-MM-dd HH:mm:ss";

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(int.class, new IntTypeEditor());
		binder.registerCustomEditor(double.class, new DoubleTypeEditor());
		binder.registerCustomEditor(float.class, new FloatTypeEditor());
		binder.registerCustomEditor(long.class, new LongTypeEditor());
		binder.registerCustomEditor(Date.class, new DateTypeEditor(DATETIME_DEFAULT_PARSE_PATTERN));
	}
}
