package com.wyu.utils;

import java.util.Random;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Created by XiaoXian on 2020/11/7.
 */
public class SaltUtil {

	public static String getSalt(int n) {
		char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz01234567890!@#$%^&*()".toCharArray();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < n; i++) {
			char aChar = chars[new Random().nextInt(chars.length)];
			stringBuilder.append(aChar);
		}
		return stringBuilder.toString();
	}

	public static void main(String[] args) {
		String salt = SaltUtil.getSalt(8);
		System.out.println(salt);
		Md5Hash md5Hash = new Md5Hash("123abc!!", salt, 1024);
		System.out.println(md5Hash.toHex());
	}
}
