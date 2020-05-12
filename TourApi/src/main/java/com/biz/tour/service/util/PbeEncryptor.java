package com.biz.tour.service.util;

import java.util.Map;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class PbeEncryptor {
	private static StandardPBEStringEncryptor pbEnc;
	static {
		pbEnc = new StandardPBEStringEncryptor();
		// 암호화를 하기위한 salt BIZ.COM 환경변수값을 사용
		Map<String, String> envList = System.getenv();
		String strSalt = envList.get("testsalt");
		pbEnc.setAlgorithm("PBEWithMD5AndDES");
		pbEnc.setPassword(strSalt);
	}

	public static String getEncrypt(String plainText) {
		return pbEnc.encrypt(plainText);
	}
	public static String getDecrypt(String encText) {
		return pbEnc.decrypt(encText);
	}
}
