package org.zk.catalina.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用于创建处理容器所有的子容器的开启关闭事件的线程的工厂
 * 
 * 使用位置：ContainerBase
 * 
 * @author king
 * @date 2019-05-04 20:49:59
 * 
 */
public class StartStopThreadFactory implements ThreadFactory {
	
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;
    
    public StartStopThreadFactory(String namePrefix) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        this.namePrefix = namePrefix;
    }
    
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(group, r, namePrefix + threadNumber.getAndIncrement());
        thread.setDaemon(true);
        return thread;
    }
}