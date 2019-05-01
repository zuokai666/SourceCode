package com.test;

import org.apache.catalina.Wrapper;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat.ExistingStandardWrapper;

import com.zk.servlet.UserServlet;

public class EngineApp {
	
	public static void main(String[] args) throws Exception {
		StandardEngine engine = new StandardEngine();
		engine.setName("Catalina");
		engine.setDefaultHost("localhost");
		{
			StandardHost host = new StandardHost();
			engine.addChild(host);
			{
				StandardContext context = new StandardContext();
				host.addChild(context);
				{
					Wrapper sw = new ExistingStandardWrapper(new UserServlet());
					context.addChild(sw);
				}
			}
		}
		engine.start();
	}
}