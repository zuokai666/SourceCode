package org.zk.catalina;

/**
 * 和线程上下文类加载器，类加载有关
 * 
 * @author king
 * @date 2019-05-03 10:47:17
 * 
 */
public interface ContextBind {
	
    ClassLoader bind(boolean usePrivilegedAction, ClassLoader originalClassLoader);
    void unbind(boolean usePrivilegedAction, ClassLoader originalClassLoader);
}