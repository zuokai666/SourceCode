package org.zk.web.servlet;

import java.util.Locale;

/**
 * 视图解析器
 * 
 * 视图与视图解析器，名词与动词分开
 * 
 * 资源与资源加载器
 * 
 * 类与类加载器
 * 
 * @author King
 *
 */
public interface ViewResolver {
	
	/**
	 * 解析给定名字的视图
	 * @param viewName
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	View resolveViewName(String viewName,Locale locale)throws Exception;
}