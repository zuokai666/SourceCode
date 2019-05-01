package com.zk.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ImageServlet implements Servlet{

	public void init(ServletConfig config) throws ServletException {
	} 
	
	public ServletConfig getServletConfig() {
		return null;
	}
	
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		res.setContentType("image/png");
		String path = "E://cat.png";
		BufferedImage bi = ImageIO.read(new File(path));
		ImageIO.write(bi, "png", res.getOutputStream());
	}
	
	public String getServletInfo() {
		return null;
	}
	
	public void destroy() {
	}

}