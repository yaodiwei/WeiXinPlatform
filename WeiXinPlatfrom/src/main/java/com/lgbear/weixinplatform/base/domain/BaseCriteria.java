package com.lgbear.weixinplatform.base.domain;


public class BaseCriteria extends BaseDomain {
	public BaseCriteria() {
		super();
	}

	public BaseCriteria(boolean isLike) {
		super();
		this.isLike = isLike;
	}

	protected boolean isLike;

	public boolean isLike() {
		return isLike;
	}

	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}

}
