package org.zk.web.servlet.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@SuppressWarnings("unused")
public abstract class AbstractTemplateView extends AbstractUrlBasedView {
	
	private boolean exposeRequestAttributes = false;
	private boolean exposeSessionAttributes = false;
	
	@Override
	public void renderMergedOutputModel(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) {
		//如果允许暴露请求属性，那就将请求属性添加进model中
		//如果允许暴露会话属性，那就将会话属性添加进model中
		renderMergedTemplateModel(model, request, response);
	}
	
	protected abstract void renderMergedTemplateModel(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response);
}