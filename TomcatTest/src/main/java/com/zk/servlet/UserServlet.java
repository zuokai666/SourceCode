package com.zk.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class UserServlet implements Servlet{

	public void init(ServletConfig config) throws ServletException {
	} 
	
	public ServletConfig getServletConfig() {
		return null;
	}

	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.err.println(1);
		res.setContentType("application/json; charset=utf-8");
		res.getOutputStream().println("{\"result\":\"1\"}");
	}
	
	public String getServletInfo() {
		return null;
	}
	
	public void destroy() {
	}

}