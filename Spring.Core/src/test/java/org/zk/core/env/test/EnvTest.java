package org.zk.core.env.test;

import org.springframework.util.PropertyPlaceholderHelper;

public class EnvTest {
	
	public static void main(String[] args) {
		PropertyPlaceholderHelper placeholderHelper = new PropertyPlaceholderHelper("#{", "}", ":", false);
		String value1 = placeholderHelper.replacePlaceholders("${java.runtime.name:def}", System.getProperties());
		System.err.println(value1);
		
		String value2 = placeholderHelper.replacePlaceholders(
				"select * from User where name = #{name}", EnvTest::resolvePlaceholder);
		System.err.println(value2);
	}
	
	public static String resolvePlaceholder(String placeholderName){
		return "?";
	}
}