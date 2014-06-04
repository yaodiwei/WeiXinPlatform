package com.lgbear.weixinplatform.base.typeeditor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

public class DateTypeEditor extends PropertyEditorSupport {
	private String parsePattern;

	public DateTypeEditor(String parsePattern) {
		this.parsePattern = parsePattern;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.isBlank(text)) {
			setValue(null);
			return;
		}

		try {
			setValue(DateUtils.parseDate(text, parsePattern));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getAsText() {
		return DateFormatUtils.format((Date) getValue(), parsePattern);
	}
}
