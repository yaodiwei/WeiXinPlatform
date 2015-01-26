package com.lgbear.weixinplatform.api.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.lgbear.weixinplatform.message.dao.TextDao;

import net.sf.json.JSONObject;

public class WeixinUtil {
	
	private static Logger log = Logger.getLogger("WXLog"); 
	
	
	private static String access_token = "";
	private static Date access_token_time = new Date(100,0,1);
	public static String getAccess_token() {
		if (new Date().getTime()-access_token_time.getTime() > 7140000){
			try {
				// 创建连接
				URL url = new URL("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx67a9d38ff7e2a347&secret=8e9bfbf5623ceece8120b5ca0a9ff135");
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
				access_token = (String)jsonObject.get("access_token");
				access_token_time = new Date();
				
				reader.close();
				// 断开连接
				connection.disconnect();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return access_token;
	}
	

    public static String loadXmlFromRequest(HttpServletRequest request) {
        ServletInputStream sis;
        String xmlDataStr = request.getParameter("xml");
        if (xmlDataStr != null && !xmlDataStr.equals("")) {
            try {
                return URLDecoder.decode(xmlDataStr, "utf-8");
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
        }
        String xmlData = null;
        try {
            sis = request.getInputStream();
            int size = request.getContentLength();
            byte[] buffer = new byte[size];
            byte[] xmldataByte = new byte[size];
            int count = 0;
            int rbyte;
            while (count < size) {
                rbyte = sis.read(buffer);
                for (int i = 0; i < rbyte; i++) {
                    xmldataByte[(count + i)] = buffer[i];
                }
                count += rbyte;
            }
            xmlData = new String(xmldataByte, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xmlData;
    }
    
    
    public static void post (String url,JSONObject json){
		log.info("String url-------"+url);
		log.info("JSONObject json-------"+json);
		HttpClientBuilder httpClientBuilder  = HttpClientBuilder.create();
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
	    HttpPost httpPost = new HttpPost(url);
	    JSONObject response = null;
	    try {
	    	StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");
	        httpPost.addHeader("content-type", "application/json");
	        httpPost.setEntity(stringEntity);
	        HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
	        if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
	            HttpEntity entity = httpResponse.getEntity();
	            log.info("HttpResponse httpResponse-------"+httpResponse);
//	            HTTP/1.1 200 OK [Server: nginx/1.4.4, Date: Wed, 03 Sep 2014 18:38:59 GMT, Content-Type: application/json; encoding=utf-8, Content-Length: 27, Connection: keep-alive]
	            
//	            entity.getContentEncoding()   为null报错
//	            String charset = entity.getContentEncoding().getValue();
	            
//				java.io.IOException: Attempted read from closed stream.
//				对Entity的读取只能运行一次  这是为什么?  下面3种方式的读取   都是一行只能运行一次
//	            response = JSONObject.fromObject(IOUtils.toString(new InputStreamReader(entity.getContent(),charset)));
//	            response = JSONObject.fromObject(IOUtils.toString(new InputStreamReader(entity.getContent())));
//	            String jsonString = EntityUtils.toString(entity);
	            
	            response = JSONObject.fromObject(EntityUtils.toString(entity));
	            
//	            log.info("String jsonString-------"+jsonString);
	            log.info("JSONObject response-------"+response);
//	            {"errcode":0,"errmsg":"ok"}
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    	try {
	    		 closeableHttpClient.close();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	    }
	}
    
    public static void post (String url,String json){
		log.info("String url-------"+url);
		log.info("String json-------"+json);
		HttpClientBuilder httpClientBuilder  = HttpClientBuilder.create();
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
	    HttpPost httpPost = new HttpPost(url);
	    JSONObject response = null;
	    try {
	    	StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");
	        httpPost.addHeader("content-type", "application/json");
	        httpPost.setEntity(stringEntity);
	        HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
	        if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
	            HttpEntity entity = httpResponse.getEntity();
	            log.info("HttpResponse httpResponse-------"+httpResponse);
//	            HTTP/1.1 200 OK [Server: nginx/1.4.4, Date: Wed, 03 Sep 2014 18:38:59 GMT, Content-Type: application/json; encoding=utf-8, Content-Length: 27, Connection: keep-alive]
	            
//	            entity.getContentEncoding()   为null报错
//	            String charset = entity.getContentEncoding().getValue();
	            
//				java.io.IOException: Attempted read from closed stream.
//				对Entity的读取只能运行一次  这是为什么?  下面3种方式的读取   都是一行只能运行一次
//	            response = JSONObject.fromObject(IOUtils.toString(new InputStreamReader(entity.getContent(),charset)));
//	            response = JSONObject.fromObject(IOUtils.toString(new InputStreamReader(entity.getContent())));
//	            String jsonString = EntityUtils.toString(entity);
	            
	            response = JSONObject.fromObject(EntityUtils.toString(entity));
	            
//	            log.info("String jsonString-------"+jsonString);
	            log.info("JSONObject response-------"+response);
//	            {"errcode":0,"errmsg":"ok"}
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    	try {
	    		 closeableHttpClient.close();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	    }
	}
    
    public static JSONObject get (String url){
		log.info("String url-------"+url);
		HttpClientBuilder httpClientBuilder  = HttpClientBuilder.create();
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
	    HttpGet httpGet = new HttpGet(url);
	    JSONObject response = null;
	    try {
	    	httpGet.addHeader("content-type", "application/json");
	        HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
	        if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
	            HttpEntity entity = httpResponse.getEntity();
	            log.info("HttpResponse httpResponse-------"+httpResponse);
//	            HTTP/1.1 200 OK [Server: nginx/1.4.4, Date: Wed, 03 Sep 2014 18:38:59 GMT, Content-Type: application/json; encoding=utf-8, Content-Length: 27, Connection: keep-alive]
	            
//	            entity.getContentEncoding()   为null报错
//	            String charset = entity.getContentEncoding().getValue();
	            
//				java.io.IOException: Attempted read from closed stream.
//				对Entity的读取只能运行一次  这是为什么?  下面3种方式的读取   都是一行只能运行一次
//	            response = JSONObject.fromObject(IOUtils.toString(new InputStreamReader(entity.getContent(),charset)));
//	            response = JSONObject.fromObject(IOUtils.toString(new InputStreamReader(entity.getContent())));
//	            String jsonString = EntityUtils.toString(entity);
	            
	            response = JSONObject.fromObject(IOUtils.toString(new InputStreamReader(entity.getContent(),"UTF-8")));
//	            String jsonString = EntityUtils.toString(entity);
//	            response = JSONObject.fromObject(jsonString);
	            
//	            log.info("String jsonString-------"+jsonString);
	            log.info("JSONObject response-------"+response);
//	            {"errcode":0,"errmsg":"ok"}
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    	try {
	    		 closeableHttpClient.close();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	    }
	    return response;
	}
   
}
