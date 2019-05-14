package com.zk.test.mbean;

import org.apache.catalina.Wrapper;
import org.apache.catalina.core.StandardWrapper;

public class MBeanTest {

	public static void main(String[] args) throws Exception {
		Wrapper sw = new StandardWrapper();
        sw.setName("StandardWrapper");
        sw.start();
        
        while(true){
			Thread.sleep(10 * 1000);
		}
	}
}