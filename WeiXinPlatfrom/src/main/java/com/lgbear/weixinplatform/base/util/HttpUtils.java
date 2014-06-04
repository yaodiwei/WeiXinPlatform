package com.lgbear.weixinplatform.base.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;

/**
 * @author yao
 * 
 *         2014年1月11日
 * 
 */
public class HttpUtils {

	public static CloseableHttpClient createClient() throws Exception {
		SSLContextBuilder builder = new SSLContextBuilder();
		builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build(), new X509HostnameVerifier() {
			public boolean verify(String arg0, SSLSession arg1) {
				return false;
			}

			public void verify(String arg0, String[] arg1, String[] arg2) throws SSLException {

			}

			public void verify(String arg0, X509Certificate arg1) throws SSLException {
			}

			public void verify(String arg0, SSLSocket arg1) throws IOException {
			}
		});

		return HttpClients.custom().setSSLSocketFactory(sslsf).build();
	}

	public static Charset getContentType(final HttpEntity entity) {
		Charset charset = null;
		final ContentType contentType = ContentType.get(entity);
		if (contentType != null) {
			charset = contentType.getCharset();
		}
		if (charset == null) {
			charset = HTTP.DEF_CONTENT_CHARSET;
		}
		return charset;
	}

	public static String toString(final InputStream instream, final Charset defaultCharset, int contentLength) throws IOException, ParseException {
		if (instream == null) {
			return null;
		}
		Args.check(contentLength <= Integer.MAX_VALUE, "HTTP entity too large to be buffered in memory");
		if (contentLength < 0) {
			contentLength = 4096;
		}
		final Reader reader = new InputStreamReader(instream, defaultCharset);
		final CharArrayBuffer buffer = new CharArrayBuffer(contentLength);
		final char[] tmp = new char[1024];
		int l;
		while ((l = reader.read(tmp)) != -1) {
			buffer.append(tmp, 0, l);
		}
		return buffer.toString();
	}
}
