package org.zk.web.servlet;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 视图基础接口
 * 
 * @author King
 *
 */
public interface View {
	
	String getContentType();
	void render(Map<String, ?> model,HttpServletRequest request,HttpServletResponse response)throws Exception;
}