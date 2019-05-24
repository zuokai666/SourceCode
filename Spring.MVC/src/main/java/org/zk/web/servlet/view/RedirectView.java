package org.zk.web.servlet.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zk.web.servlet.SmartView;
@SuppressWarnings("unused")
public class RedirectView extends AbstractUrlBasedView implements SmartView{

	//http1.0是否兼容
	private boolean http10Compatible = true;
	
	public String getContentType() {
		return null;
	}

	@Override
	public void renderMergedOutputModel(Map<String, ?> model, HttpServletRequest request,
			HttpServletResponse response) {
	}

	public boolean isRedirectView() {
		return true;
	}
}