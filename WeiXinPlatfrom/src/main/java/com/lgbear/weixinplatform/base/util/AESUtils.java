package com.lgbear.weixinplatform.base.util;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import org.apache.commons.codec.binary.Base64;

public class AESUtils {

	public static String encrypt(String plainText, String keyString) {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, getKey(keyString));
			byte[] bytePlainText = plainText.getBytes("UTF-8");
			byte[] byteCipherText = cipher.doFinal(bytePlainText);
			return Base64.encodeBase64String(byteCipherText);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String decrypt(String cipherText, String keyString) {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, getKey(keyString));
			byte[] byteCipherText = Base64.decodeBase64(cipherText);
			byte[] bytePlainText = cipher.doFinal(byteCipherText);
			return new String(bytePlainText, "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static Key getKey(String keyString) {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(keyString.getBytes("UTF-8"));
			keyGenerator.init(secureRandom);
			return keyGenerator.generateKey();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
