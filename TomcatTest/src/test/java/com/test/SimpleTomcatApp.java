package com.test;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import com.zk.servlet.UserServlet;

public class SimpleTomcatApp {
	
	public static void main(String[] args) throws Exception {
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);//设置端口
		{
			Context context=tomcat.addContext("myapp", null);
			Tomcat.addServlet(context, "userServlet",new UserServlet());
			context.addServletMappingDecoded("/userServlet","userServlet");//配置servlet映射路径
		}
		tomcat.start();
		tomcat.getServer().await();
	}
}