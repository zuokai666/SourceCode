package org.zk.tomcat.util;

public class ExceptionUtils {
	
	/**
	 * 拦截所有的异常，默认先所有抛出
	 */
	public static void handleThrowable(Throwable t) {
		if(t instanceof Exception){
			//do nothing.
		}else {
			t.printStackTrace();
		}
    }
}