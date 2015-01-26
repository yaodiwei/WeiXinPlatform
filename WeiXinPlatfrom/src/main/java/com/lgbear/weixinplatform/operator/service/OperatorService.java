package com.lgbear.weixinplatform.operator.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lgbear.weixinplatform.api.util.WeixinUtil;
import com.lgbear.weixinplatform.base.domain.Pagination;
import com.lgbear.weixinplatform.message.dao.TextDao;
import com.lgbear.weixinplatform.message.domain.Text;
import com.lgbear.weixinplatform.operator.dao.OperatorDao;
import com.lgbear.weixinplatform.operator.domain.Operator;
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
import javax.servlet.http.HttpServletResponse;

@Service
public class OperatorService {
	
	@Resource
	OperatorDao operatorDao;
	
	public Operator find(String loginName) {
		return operatorDao.find(loginName);
	}

	public void update(Operator operator) {
		operatorDao.update(operator);
	}
}
