package com.frameworks.selenium.utils;

public class StringUtils {

	public static String camelToWords(String str) {
		String result = "";
		char c = str.charAt(0);
		result = result + Character.toUpperCase(c);
		for (int i = 1; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (Character.isUpperCase(ch)) {
				result = result + ' ';
				result = result + Character.toUpperCase(ch);
			} else {
				result = result + ch;
			}
		}
		return result;
	}
}
