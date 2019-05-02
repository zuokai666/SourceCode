package org.zk.catalina;

/**
 * 这是一个展现了整个Catalina引擎的容器，它在以下场景中很有用：
 * 1.你希望通过使用拦截器来看看每个请求处理过程
 * 2.你希望支持多个虚拟主机
 * 
 * 引擎处于容器继承关系的最顶端，因此它的setParent()应该抛出异常
 * 
 * @author king
 * @date 2019-05-02 21:17:47
 *
 */
public interface Engine extends Container{
	
	//3个属性
	String getDefaultHost();
	void setDefaultHost(String host);
	String getJvmRoute();
	void setJvmRoute(String jvmRouteId);
	Service getService();
	void setService(Service service);
}