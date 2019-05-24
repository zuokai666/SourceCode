package org.zk.web.servlet.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zk.web.servlet.View;

public abstract class AbstractView implements View{
	
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)throws Exception {
		//模板设计模式
	}
	
	public abstract void renderMergedOutputModel(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response);
}