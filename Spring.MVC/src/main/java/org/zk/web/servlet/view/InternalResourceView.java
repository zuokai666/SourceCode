package org.zk.web.servlet.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 表示JSP视图
 * 
 * @author King
 * 
 */
public class InternalResourceView extends AbstractUrlBasedView {
	
	public String getContentType() {
		return null;
	}
	
	@Override
	public void renderMergedOutputModel(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) {
		
	}
}