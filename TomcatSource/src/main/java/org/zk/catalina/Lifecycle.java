package org.zk.catalina;

/**
 * 组件生命周期方法的通用接口
 * 当状态改变时，生命周期事件将会被触发
 * 
 * @author king
 * @date 2019-05-02 16:22:36
 * 
 */
public interface Lifecycle {
	
	String PERIODIC_EVENT = "periodic";
	/**
	 * 在组件启动前的一些准备工作，在方法前后触发两个事件
	 */
	void init() throws LifecycleException;
	String BEFORE_INIT_EVENT = "before_init";
	String AFTER_INIT_EVENT = "after_init";
	
	/**
	 * 方法前触发第一个，方法中触发第二个，方法后触发第三个
	 */
	void start() throws LifecycleException;
    String BEFORE_START_EVENT = "before_start";
    String CONFIGURE_START_EVENT = "configure_start";
    String START_EVENT = "start";
    String AFTER_START_EVENT = "after_start";
    
    void stop() throws LifecycleException;
    String BEFORE_STOP_EVENT = "before_stop";
    String STOP_EVENT = "stop";
    String CONFIGURE_STOP_EVENT = "configure_stop";
    String AFTER_STOP_EVENT = "after_stop";
    
    void destroy() throws LifecycleException;
    String BEFORE_DESTROY_EVENT = "before_destroy";
    String AFTER_DESTROY_EVENT = "after_destroy";
    
    /**
     * 生命周期监听器的管理器，提供了增删改查的方法
     */
    void addLifecycleListener(LifecycleListener listener);
    LifecycleListener[] findLifecycleListeners();
    void removeLifecycleListener(LifecycleListener listener);
    
    /**
     * 得到当前组件的状态
     */
    LifecycleState getState();
    String getStateName();
}