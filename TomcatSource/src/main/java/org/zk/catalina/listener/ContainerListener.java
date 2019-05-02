package org.zk.catalina.listener;

import java.util.EventListener;

import org.zk.catalina.event.ContainerEvent;

/**
 * 容器有关的监听器
 * 
 * @author king
 * @date 2019-05-02 20:31:47
 *
 */
public interface ContainerListener extends EventListener{
	
	void containerEvent(ContainerEvent containerEvent);
}