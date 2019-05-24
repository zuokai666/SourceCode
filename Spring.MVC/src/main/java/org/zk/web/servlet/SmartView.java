package org.zk.web.servlet;

/**
 * 通过继承的方式添加一些额外信息
 * 
 * 符合开闭原则
 * 
 * @author King
 *
 */
public interface SmartView extends View {
	
	boolean isRedirectView();
}