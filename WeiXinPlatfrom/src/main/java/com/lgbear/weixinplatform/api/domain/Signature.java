package com.lgbear.weixinplatform.api.domain;

import com.lgbear.weixinplatform.base.domain.BaseDomain;

/**
 * 
 * @author yaodiwei
 * 
 *         微信签名使用
 */
public class Signature extends BaseDomain {

	private String signature;
	private String timestamp;
	private String nonce;
	private String echostr;
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public String getEchostr() {
		return echostr;
	}
	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}
	
	
	
}
