package org.zk.catalina;

import java.io.IOException;

import javax.servlet.ServletException;

import org.zk.catalina.connector.Request;
import org.zk.catalina.connector.Response;

/**
 * 阀门是一个和容器关联的处理请求的组件，一系列的阀门组成一个管道，详细的阀门协议在下面的invoke方法的描述中
 * 历史注解：被赋予阀门这个概念是因为：你在现实生活中用管道通过阀门来控制和改变流向
 * 
 * @author king
 * @date 2019-05-02 12:07:54
 */
public interface Valve {
	
	Valve getNext();
	/**
	 * 个人感觉：就像是链表中的节点，链表是管道，节点是阀门，并且是单链表，因为只有设置和获得下一个阀门
	 */
	void setNext();
	
	/**
	 * 执行一个周期性的任务，例如重新加载等等，这个方法将会被容器的类加载上下文调用，未预料的错误将会被捕捉与记录
	 */
	void backgroundProcess();
	
	/**
	 * 是否支持异步
	 */
	boolean isAsyncSupported();
	/**
	 * 根据阀门的要求执行请求处理
	 * 一个阀门根据指定的顺序可以执行以下的操作：
	 * 1.检查，修改请求，响应的属性
	 * 2.检查请求的属性，生成一个相一致的响应，返回控制权给调用者
	 * 3.检查，修改请求，响应的属性，然后包装他们来补充功能，传递他们
	 * 4.如果正确的响应没有产生，控制权也没有返回，通过执行代码"getNext().invoke()"叫管道中的下一个阀门运行，
	 * 5.检查，但是不修改响应的属性
	 * 
	 * 一个阀门绝对不可以做的操作：
	 * 1.改变已经传递下去的请求属性
	 * 2.创建一个已经完成的响应，传递给下一个阀门
	 * 3.消费请求中的输入流字节，除非已经生成响应，或者在传递请求之前已经包装了
	 * 4.在下一个阀门请求返回后，修改响应中的HTTP头部信息
	 * 5.在下一个阀门请求返回后，执行任何有关输出流的操作
	 */
	void invoke(Request request,Response response) throws IOException,ServletException;
}