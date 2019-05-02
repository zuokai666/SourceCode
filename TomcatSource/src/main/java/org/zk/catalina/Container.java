package org.zk.catalina;

import java.beans.PropertyChangeListener;
import java.io.File;

import javax.management.ObjectName;

import org.zk.catalina.connector.Request;
import org.zk.catalina.connector.Response;
import org.zk.catalina.listener.ContainerListener;

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
    /**
     * child的setParent()方法一定会被调用
     */
    void addChild(Container child);
    Container findChild(String name);
    Container[] findChildren();
    void removeChild(Container child);
    
    void addContainerListener(ContainerListener listener);
    ContainerListener[] findContainerListeners();
    void removeContainerListener(ContainerListener listener);
    void fireContainerEvent(String type, Object data);
    
    void addPropertyChangeListener(PropertyChangeListener listener);
    void removePropertyChangeListener(PropertyChangeListener listener);
    
    void logAccess(Request request,Response response,long time,boolean useDefault);
    AccessLog getAccessLog();
    int getStartStopThreads();
    void setStartStopThreads(int startStopThreads);
    File getCatalinaBase();
    File getCatalinaHome();
}