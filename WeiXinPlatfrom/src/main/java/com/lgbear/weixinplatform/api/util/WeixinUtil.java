package com.lgbear.weixinplatform.api.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

public class WeixinUtil {

    public static String loadXmlFromRequest(HttpServletRequest request) {
        ServletInputStream sis;
        String xmlDataStr = request.getParameter("xml");
        if (xmlDataStr != null && !xmlDataStr.equals("")) {
            try {
                return URLDecoder.decode(xmlDataStr, "utf-8");
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(WeixinUtil.class.getName()).log(Level.SEVERE, null, ex);
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
}
