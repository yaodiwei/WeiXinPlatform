package com.lgbear.weixinplatform.base.typeeditor;

import java.beans.PropertyEditorSupport;

public class LongTypeEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text == null || text.equals(""))
			text = "0";
		setValue(Long.valueOf(text));
	}
}
