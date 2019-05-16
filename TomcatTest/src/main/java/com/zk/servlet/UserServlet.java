package com.zk.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.session.StandardSessionFacade;

public class UserServlet extends HttpServlet{
	
	private static final long serialVersionUID = -1523386767224719857L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		StandardSessionFacade session = (StandardSessionFacade) req.getSession();
		String user = (String) session.getAttribute("user");
		if(user == null){
			System.err.println("登录");
			session.setAttribute("user", "zuokai");
		}
		System.err.println(session.getAttribute("user"));
		System.err.println(session.getClass());
		res.setContentType("application/json; charset=utf-8");
		res.getOutputStream().println("{\"result\":\"1\"}");
	}
}