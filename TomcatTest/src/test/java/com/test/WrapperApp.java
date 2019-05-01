package com.test;

import org.apache.catalina.LifecycleException;

import com.zk.component.StandardBox;

public class WrapperApp {
	
	public static void main(String[] args) throws LifecycleException {
//		StandardWrapper wrapper = new StandardWrapper();
//		wrapper.setName("wrapper");
//		wrapper.setServlet(new UserServlet());
//		wrapper.start();
//		
//		Pipeline pipeline = new StandardPipeline();
//		
		
		StandardBox box = new StandardBox();
		box.start();
	}
}