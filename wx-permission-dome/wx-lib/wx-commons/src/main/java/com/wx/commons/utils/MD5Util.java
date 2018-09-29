package com.wx.commons.utils;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

/**
 * @Author wx
 * @Description MD5加密工具类
 * @Date 2018-8-86
 */
@Slf4j
public class MD5Util {

	/**
	 * @methodName: encrypt
	 * @author: wx
	 * @description: md5加密
	 * @param s
	 * @date: 2018/8/26
	 * @return: java.lang.String
	 */
	public final static String encrypt(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			log.error("generate md5 error, {}", s, e);
			return null;
		}
	}



	/**
	 * @methodName: getrandomSixNum
	 * @author: wx
	 * @description: 生成随机六位数
	 * @param
	 * @date: 2018/8/26
	 * @return: int
	 */
	public static int getrandomSixNum() {
		return (int) ((Math.random() * 9 + 1) * 100000);
	}

}
