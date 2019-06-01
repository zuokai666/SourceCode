package org.zk.application.context.test;

import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;

public class AppContextTest {

	public static void main(String[] args) {
		AnnotationConfigServletWebServerApplicationContext appContext = new AnnotationConfigServletWebServerApplicationContext();
		appContext.close();
	}
}