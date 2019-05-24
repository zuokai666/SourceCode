package org.zk.web.servlet.view;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.zk.web.servlet.View;
import org.zk.web.servlet.ViewResolver;

/**
 * 缓存功能的视图解析器
 * 
 * @author King
 *
 */
public abstract class AbstractCachingViewResolver implements ViewResolver {
	
	@SuppressWarnings("unused")
	private final Map<Object, View> viewCreationCache = new LinkedHashMap<>();
	@SuppressWarnings("unused")
	private volatile int cacheLimit = 1024;
	
	
	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		return null;
	}
}