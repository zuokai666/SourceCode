package org.zk.core.convert.test;

import java.util.List;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

public class ConversionTest {

	public static void main(String[] args) {
		ConversionService conversionService = DefaultConversionService.getSharedInstance();
//		System.err.println(conversionService.convert("2a", Integer.class));
		System.err.println(conversionService.convert("${JAVA_HOME}", List.class));
	}
}