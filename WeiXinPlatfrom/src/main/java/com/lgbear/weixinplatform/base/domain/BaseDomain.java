package com.lgbear.weixinplatform.base.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class BaseDomain {
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
