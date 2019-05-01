package com.test;

import java.io.File;
import java.io.IOException;

import org.apache.catalina.Context;
import org.apache.catalina.Engine;
import org.apache.catalina.Globals;
import org.apache.catalina.Host;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.core.StandardService;
import org.apache.catalina.realm.NullRealm;
import org.apache.catalina.servlets.DefaultServlet;
import org.apache.catalina.startup.Tomcat.ExistingStandardWrapper;
import org.apache.catalina.startup.Tomcat.FixContextListener;

import com.zk.servlet.ImageServlet;
import com.zk.servlet.UserServlet;

public class ComplexTomcatApp {
	
	public static int port = 8080;
	public static File userDir = getUserDir();
	public static String hostname = "localhost";
	
	static{
		System.setProperty("catalina.useNaming", "false");
		System.setProperty(Globals.CATALINA_BASE_PROP, userDir.getPath());
        System.setProperty(Globals.CATALINA_HOME_PROP, userDir.getPath());
	}
	
	public static void main(String[] args) throws Exception {
		StandardServer tomcatServer = new StandardServer();
		tomcatServer.setPort(-1);
		tomcatServer.setCatalinaBase(userDir);
		tomcatServer.setCatalinaHome(userDir);
		{
			StandardService service = new StandardService();
	        service.setName("Tomcat");
	        tomcatServer.addService(service);
	        {
    			Connector connector = new Connector("HTTP/1.1");
    	        connector.setPort(port);
    	        service.addConnector(connector);
    		}
	        {
	        	Engine engine = new StandardEngine();
	            engine.setName("Tomcat");
	            engine.setDefaultHost("localhost");
	            engine.setRealm(new NullRealm());
	            service.setContainer(engine);
	            {
	            	Host host = new StandardHost();
	                host.setName("localhost");
	                engine.addChild(host);
	                {
	                	Context ctx = new StandardContext();
	                    ctx.setName("myapp");
	                    ctx.setPath("myapp");
	                    ctx.setDocBase("myapp");
	                    ctx.addLifecycleListener(new FixContextListener());
	                    host.addChild(ctx);
	                    {
	                    	Wrapper sw = new ExistingStandardWrapper(new UserServlet());
	                        sw.setName("userServlet");
	                        ctx.addChild(sw);
	                        ctx.addServletMappingDecoded("/userServlet","userServlet");
	                    }
	                    {
	                    	Wrapper sw = new ExistingStandardWrapper(new ImageServlet());
	                        sw.setName("imageServlet");
	                        ctx.addChild(sw);
	                        ctx.addServletMappingDecoded("/imageServlet","imageServlet");
	                    }
	                    {
	                    	Wrapper sw = new ExistingStandardWrapper(new DefaultServlet());
	                        sw.setName("defaultServlet");
	                        ctx.addChild(sw);
	                        ctx.addServletMappingDecoded("/static/*","defaultServlet");
	                    }
	                }
	            }
	        }
		}
		tomcatServer.start();
		tomcatServer.await();
	}
	
	public static File getUserDir(){
		String basedir = System.getProperty("user.dir") + "/tomcat." + port;
        File baseFile = new File(basedir);
        baseFile.mkdirs();
        try {
            baseFile = baseFile.getCanonicalFile();
        } catch (IOException e) {
            baseFile = baseFile.getAbsoluteFile();
        }
        return baseFile;
	}
}