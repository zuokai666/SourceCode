package org.zk.catalina.core;

import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

/**
 * StandardWrapper的门面
 * 
 * @author King
 *
 */
public final class StandardWrapperFacede implements ServletConfig{
	
	private final ServletConfig config;
	
	private ServletContext context;
	
	public StandardWrapperFacede(ServletConfig config) {
		this.config = config;
	}
	
	@Override
	public String getServletName() {
		return config.getServletName();
	}
	
	@Override
	public ServletContext getServletContext() {
		if(context == null){
			context = config.getServletContext();
			if (context instanceof ApplicationContext) {
                context = ((ApplicationContext) context).getFacade();
            }
		}
		return context;
	}
	
	@Override
	public String getInitParameter(String name) {
		return config.getInitParameter(name);
	}
	
	@Override
	public Enumeration<String> getInitParameterNames() {
		return config.getInitParameterNames();
	}
}