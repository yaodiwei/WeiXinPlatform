package com.lgbear.weixinplatform.base.typeeditor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.StringUtils;

public class DoubleTypeEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.isEmpty(text))
			text = "0";
		setValue(Double.valueOf(text));
	}
}
