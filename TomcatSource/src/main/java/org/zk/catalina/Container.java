package org.zk.catalina;

import javax.management.ObjectName;

/**
 * 容器是一个处理请求，返回响应的对象
 * 通常的具体实现是：Engine，Host，Context，Wrapper
 * 
 * 改动：去掉了Logger日志组件，直接在每个类中直接添加slf4j的静态变量进行使用
 * 
 * @author king
 * @date 2019-05-02 17:36:11
 * 
 */
public interface Container extends Lifecycle{
	
	/**
	 * 容器事件
	 */
	String ADD_CHILD_EVENT = "addChild";
    String ADD_VALVE_EVENT = "addValve";
    String REMOVE_CHILD_EVENT = "removeChild";
    String REMOVE_VALVE_EVENT = "removeValve";
    
    // ------------------------------------------------------------- Properties
    /**
     * JMX相关方法
     */
    ObjectName getObjectName();
    String getDomain();
    String getMBeanKeyProperties();
    
    Pipeline getPipeline();
    Cluster getCluster();
    void setCluster(Cluster cluster);
    
    int getBackgroundProcessorDelay();
    void setBackgroundProcessorDelay(int delay);
    
    /**
     * 独一无二的名字
     */
    String getName();
    void setName(String name);
    
    Container getParent();
    void setParent(Container parent);
    
    ClassLoader getParentClassLoader();
    void setParentClassLoader(ClassLoader parent);
    
    Realm getRealm();
    void setRealm(Realm realm);
    
    // --------------------------------------------------------- Public Methods
    /**
     * 阀门也有一个同样的方法
     */
    void backgroundProcess();
    void addChild(Container child);
    
    
}