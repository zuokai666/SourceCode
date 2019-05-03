package org.springframework.util;

public abstract class StringUtils {
	
	/**
	 * 全部是空格也是有长度
	 */
	public static boolean hasLength(String str) {
		return (str != null && !str.isEmpty());
	}
	
	/**
	 * 不仅有长度，还要至少有个字母，才算是有内容
	 */
	public static boolean hasText(String str) {
		return hasLength(str) && containsText(str);
	}
	
	private static boolean containsText(CharSequence str) {
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}
}