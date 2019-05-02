package org.zk.catalina.listener;

import java.util.EventListener;

import org.zk.catalina.event.LifecycleEvent;

/**
 * 生命周期有关的监听器
 * 
 * @author king
 * @date 2019-05-02 20:32:05
 * 
 */
public interface LifecycleListener extends EventListener{
	
	void lifecycleEvent(LifecycleEvent lifecycleEvent);
}