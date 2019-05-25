package org.zk.catalina.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.management.NotificationBroadcasterSupport;
import javax.management.NotificationEmitter;
import javax.management.ObjectName;
import javax.servlet.MultipartConfigElement;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zk.catalina.Wrapper;

/**
 * 一个Wrapper的标准实现，展现了一个Servlet定义，它不允许有子容器，它的父容器必须是Context
 * 
 * javax.servlet的SingleThreadModel单线程模型，保证servlet同一时间只能处理一个请求
 * 
 * 
 * @author King
 *
 */
@SuppressWarnings("unused")
public abstract class StandardWrapper extends ContainerBase implements ServletConfig,Wrapper,NotificationEmitter{
	
	public static final Logger log = LoggerFactory.getLogger(StandardWrapper.class);
	protected static final String[] default_servlet_methods = new String[]{"GET","HEAD","POST"};
	
	//如果为0，就是有效的；如果为Long的最大值，就是永久失效
	protected long available = 0l;
	//发送通知
	protected final NotificationBroadcasterSupport broadcaster;
	//当前分配的实例的激活的计数
	protected final AtomicInteger countAllocated = new AtomicInteger();
	//门面
	protected final StandardWrapperFacede facade = new StandardWrapperFacede(this);
	//没有初始化的Servlet实例
	protected volatile Servlet instance = null;
	//当前实例是否实例化的标志
	protected volatile boolean instanceInitialized = false;
	//load-on-startup先后顺序（负值意味着最先启动）
	protected int loanOnStartup = -1;
	//run-as的标识
	protected String runAs = null;
	//通知的序列号
	protected long sequenceNumber = 0;
	//Servlet的全限定名
	protected String servletClass = null;
	//Servlet类是否实现了STM接口
	protected volatile boolean singleTreadModel = false;
	//我们是不是正在卸载实例
	protected volatile boolean unloading = false;
	//最大的STM实例个数
	protected int maxInstances = 20;
	protected int nInstances = 0;
	//STM的实例栈
	protected Stack<Servlet> instancePool = null;
	//实例卸载的等待时间ms
	protected int unloadDelay = 2000;
	//是否是JspServlet
	protected boolean isJspServlet;
	
	protected ObjectName jspMonitorON;
	//我们是否应该吞下System.out
	protected boolean swallowOutput = false;
	// To support jmx attributes
    protected StandardWrapperValve swValve;
    protected long loadTime=0;
    protected int classLoadTime=0;
    protected MultipartConfigElement multipartConfigElement = null;
    
    protected boolean asyncSupported = false;
    protected boolean enabled = true;
    protected boolean overridable = false;
  //这个包装器关联的映射
  	protected ArrayList<String> mappings = new ArrayList<>();
  	//这个Servlet初始化的参数，键是参数名称
  	protected HashMap<String, String> parameters = new HashMap<>();
  	//安全角色有关
  	protected HashMap<String, String> references = new HashMap<>();
  	
    private final ReentrantReadWriteLock mappingsLock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock parametersLock = new ReentrantReadWriteLock();
	private final ReentrantReadWriteLock referencesLock = new ReentrantReadWriteLock();
    
    
	public StandardWrapper() {
		this.broadcaster = new NotificationBroadcasterSupport();
	}
	
	@Override
	public boolean isOverridable() {
		return overridable;
	}

	@Override
	public void setOverridable(boolean overridable) {
		this.overridable = overridable;
	}
	
	@Override
	public long getAvailable() {
		return available;
	}
	
	@Override
	public void setAvailable(long available) {
		long oldAvailable = this.available;
		if(System.currentTimeMillis() < available){
			this.available = available;
		}else {
			this.available = 0l;
		}
		support.firePropertyChange("available", oldAvailable, available);
	}
	public int getCountAllocated() {
        return this.countAllocated.get();
    }
	@Override
	public int getLoadOnStartup() {
		if(isJspServlet && loanOnStartup < 0){
			return Integer.MAX_VALUE;
		}else {
			return loanOnStartup;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}