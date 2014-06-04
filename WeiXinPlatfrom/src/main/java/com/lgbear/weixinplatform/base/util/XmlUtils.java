package com.lgbear.weixinplatform.base.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import com.lgbear.weixinplatform.base.annotation.XmlCollection;
import com.lgbear.weixinplatform.base.annotation.XmlFieldName;
import com.lgbear.weixinplatform.base.annotation.XmlRootName;


/**
 * @author caiguohui
 * 
 *         2014年1月11日
 * 
 */
public class XmlUtils {

	/**
	 * 根据xml字符串获取Document对象
	 * 
	 * @param xmlStr
	 * @return
	 * @throws DocumentException
	 */
	public static Document getDocument(String xmlStr) throws DocumentException {
		return new SAXReader().read(new ByteArrayInputStream(xmlStr.getBytes()));
	}

	/**
	 * 根据流获取Document对象
	 * 
	 * @param inputStream
	 * @return
	 * @throws DocumentException
	 */
	public static Document getDocument(InputStream inputStream) throws DocumentException {
		return new SAXReader().read(inputStream);
	}

	/**
	 * 根据对象解析成xml格式的字符串
	 * 
	 * @param o
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getXmlByObject(Object o) {
		StringBuffer result = new StringBuffer("<?xml version='1.0' encoding='utf-8'?>");
		String rootName = null;
		Class<?> clazz = o.getClass();
		Annotation[] annotations = clazz.getAnnotations();
		for (Annotation annotation : annotations) {
			if (annotation instanceof XmlRootName) {
				XmlRootName root = (XmlRootName) annotation;
				rootName = root.value();
				break;
			}
		}
		if (rootName == null)
			throw new IllegalArgumentException("找不到Root");
		result.append("<" + rootName + ">");
		Field[] fields = clazz.getDeclaredFields();
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				annotations = field.getAnnotations();
				for (Annotation annotation : annotations) {
					if (annotation instanceof XmlCollection) {
						XmlCollection childName = (XmlCollection) annotation;
						result.append("<" + childName.value() + ">");
						if (field.get(o) != null) {
							Collection<Object> child = (Collection<Object>) field.get(o);
							for (Object childObject : child) {
								Field[] childFields = childObject.getClass().getDeclaredFields();
								for (Field childField : childFields) {
									childField.setAccessible(true);
									Annotation[] childAnnotations = childField.getAnnotations();
									for (Annotation childAnnotation : childAnnotations) {
										if (childAnnotation instanceof XmlFieldName) {
											XmlFieldName childName2 = (XmlFieldName) childAnnotation;
											result.append("<" + childName2.value() + ">");
											try {
												result.append(childField.get(childObject));
											} catch (Exception e) {
												AppUtils.recordException(e);
											}
											result.append("</" + childName2.value() + ">");
											break;
										}
									}
								}
							}
						}
						result.append("</" + childName.value() + ">");
					} else if (annotation instanceof XmlFieldName) {
						XmlFieldName childName = (XmlFieldName) annotation;
						result.append("<" + childName.value() + ">");
						try {
							result.append(field.get(o));
						} catch (Exception e) {
							AppUtils.recordException(e);
						}
						result.append("</" + childName.value() + ">");
						break;
					}
				}
			}
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
			AppUtils.recordException(e);
		}
		result.append("</" + rootName + ">");
		return result.toString();
	}
}
