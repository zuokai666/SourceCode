package org.zk.catalina;

import java.util.Set;

/**
 * 这个接口描述了阀门的集合，当调用阀门的invoke方法时，集合的阀门将串联执行，管道某处的阀门必须处理请求，
 * 生成相一致的响应，而不是试图传递请求。
 * 一个管道要与容器关联，Basic（必须）阀门要最后执行，其他阀门按照顺序在它之前执行
 * 
 * 个人理解：阀门管理器，提供了对阀门增删改查的接口方法
 * 
 * @author king
 * @date 2019-05-02 12:07:54
 */
public interface Pipeline extends ContainerAware{
	
	Valve getBasic();
	/**
	 * 如果实现了Contained接口，将会调用阀门的setContainer()方法
	 */
	void setBasic(Valve basic);
	/**
	 * 添加阀门到管道末尾
	 * 如果实现了Contained接口，将会调用阀门的setContainer()方法
	 * 如果调用成功，将会调用关联容器的ADD_VALVE_EVENT添加阀门事件
	 */
	void addValve(Valve valve);
	/**
	 * 如果没有阀门将会返回长度为零的数组
	 */
	Valve[] getValves();
	/**
	 * 如果阀门找到被删除，并且实现了Contained接口，将会调用阀门的setContainer(null)方法
	 * 如果调用成功，将会调用关联容器的REMOVE_VALVE_EVENT移除阀门事件
	 */
	void removeValve(Valve valve);
	
	Valve getFirst();
	/**
	 * 所有阀门都支持异步则返回true，否则返回false
	 */
	boolean isAsyncSupported();
	
    /**
     * 找到非异步阀门，添加进set集合
     */
    void findNonAsyncValves(Set<String> result);
}