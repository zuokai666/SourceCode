package org.zk.catalina.core;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.zk.catalina.Globals;
import org.zk.catalina.LifecycleState;
import org.zk.catalina.event.ContainerEvent;
import org.zk.catalina.Valve;
import org.zk.catalina.connector.Request;
import org.zk.catalina.connector.Response;
import org.zk.catalina.core.AccessLogAdapter;
import org.zk.catalina.thread.StartStopThreadFactory;
import org.zk.tomcat.util.MultiThrowable;
import org.springframework.util.Assert;
import org.zk.catalina.AccessLog;
import org.zk.catalina.Cluster;
import org.zk.catalina.Container;
import org.zk.catalina.Lifecycle;
import org.zk.catalina.LifecycleException;
import org.zk.catalina.Pipeline;
import org.zk.catalina.Realm;
import org.zk.catalina.listener.ContainerListener;
import org.zk.catalina.util.LifecycleMBeanBase;
import org.zk.tomcat.util.res.StringManager;

/**
 * 容器的抽象基类，提供了每个容器实现的通用的功能，继承这个类的类必须实现invoke()方法的替代
 * 这里提到了“Chain of Responsibility”责任链设计模式
 * 一个子类应该有自己的标准阀门，并设置为管道的基本阀门
 * 
 * @author king
 * @date 2019-05-04 15:04:45
 */
public abstract class ContainerBase extends LifecycleMBeanBase implements Container{
	
	public static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ContainerBase.class);
	public static final StringManager sm = StringManager.getManager(ContainerBase.class);
	
	protected final HashMap<String, Container> children = new HashMap<>();
	protected int backgroundProcessorDelay = -1;
	protected final List<ContainerListener> listeners = new CopyOnWriteArrayList<>();
	//簇
	protected Cluster cluster = null;
	private final ReadWriteLock clusterLock = new ReentrantReadWriteLock();
	protected String name = null;
	protected Container parent;
	protected ClassLoader parentClassLoader;
	protected final Pipeline pipeline = new StandardPipeline(this);
	private volatile Realm realm = null;
	private final ReadWriteLock realmLock = new ReentrantReadWriteLock();
	//是否添加子容器时，直接启动它们
	protected boolean startChildren = true;
	protected final PropertyChangeSupport support = new PropertyChangeSupport(this);
	//后台线程
	private Thread thread = null;
	//后台线程完成信号量
	private volatile boolean threadDone = false;
	protected volatile AccessLog accessLog = null;
	private volatile boolean accessLogScanComplete = false;
	private int startStopThreads = 1;
	protected ThreadPoolExecutor startStopExecutor;
	
	@Override
	public int getStartStopThreads() {
		return startStopThreads;
	}
	
	//处理特殊值
	public int getStartStopThreadsInternal() {
		int result = getStartStopThreads();
		if(0 < result){
			return result;
		}
		//本来=0，result=CPU
		//本来<0，result=CPU + result
		result += Runtime.getRuntime().availableProcessors();
		if(result < 1){
			result = 1;
		}
		return result;
	}
	
	@Override
	public void setStartStopThreads(int startStopThreads) {
		this.startStopThreads = startStopThreads;
		//使用本地副本，保证线程安全
		ThreadPoolExecutor threadPoolExecutor = this.startStopExecutor;
		if(threadPoolExecutor != null){
			int newSize = getStartStopThreadsInternal();
			threadPoolExecutor.setCorePoolSize(newSize);
			threadPoolExecutor.setMaximumPoolSize(newSize);
		}
	}
	
	@Override
	public int getBackgroundProcessorDelay() {
		return backgroundProcessorDelay;
	}
	
	@Override
	public void setBackgroundProcessorDelay(int delay) {
		this.backgroundProcessorDelay = delay;
	}
	
	/**
	 * 读方法使用readLock
	 */
	@Override
	public Cluster getCluster() {
		Lock readLock = clusterLock.readLock();
		readLock.lock();
		try {
			if(cluster != null) return cluster;
			if(parent != null) return parent.getCluster();
			return null;
		} finally {
			readLock.unlock();
		}
	}
	//加锁，线程安全
	protected Cluster getClusterInternal(){
		Lock readLock = clusterLock.readLock();
		readLock.lock();
		try {
			return cluster;
		} finally {
			readLock.unlock();
		}
	}
	@Override
	public void setCluster(Cluster cluster) {
		Cluster oldCluster = null;
		Cluster newCluster = null;
		Lock writeLock = clusterLock.writeLock();
		writeLock.lock();
		try {
			oldCluster = this.cluster;
			newCluster = cluster;
			//检查状态改变是否有必要
			if(oldCluster == newCluster){
				return;
			}
			this.cluster = newCluster;
			//如果有必要，停止旧组件
			if(getState().isAvailable() && (oldCluster != null) && (oldCluster instanceof Lifecycle)){
				try {
					((Lifecycle)oldCluster).stop();
				} catch (LifecycleException e) {
					log.error("ContainerBase.setCluster: stop: ", e);
				}
			}
			//如果有必要，开启新组件
			if(cluster != null){
				cluster.setContainer(this);
			}
			if(getState().isAvailable() && (cluster != null) && (cluster instanceof Lifecycle)){
				try {
					((Lifecycle)cluster).start();
				} catch (LifecycleException e) {
					log.error("ContainerBase.setCluster: start: ", e);
				}
			}
		} finally {
			writeLock.unlock();
		}
		//报告这个属性改变给感兴趣的观察者们
		support.firePropertyChange("cluster", oldCluster, newCluster);
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		Assert.notNull(name, sm.getString("containerBase.nullName"));
		String oldName = this.name;
		String newName = name;
		this.name = newName;
		support.firePropertyChange("name", oldName, newName);
	}
	public boolean getStartChildren() {
        return startChildren;
    }
	public void setStartChildren(boolean startChildren) {
        boolean oldStartChildren = this.startChildren;
        this.startChildren = startChildren;
        support.firePropertyChange("startChildren", oldStartChildren, this.startChildren);
    }
	@Override
    public Container getParent() {
        return parent;
    }
	@Override
    public void setParent(Container container) {
        Container oldParent = this.parent;
        this.parent = container;
        support.firePropertyChange("parent", oldParent, this.parent);
    }
	@Override
    public ClassLoader getParentClassLoader() {
        if (parentClassLoader != null)
            return (parentClassLoader);
        if (parent != null) {
            return (parent.getParentClassLoader());
        }
        return (ClassLoader.getSystemClassLoader());
    }
	@Override
    public void setParentClassLoader(ClassLoader parent) {
        ClassLoader oldParentClassLoader = this.parentClassLoader;
        this.parentClassLoader = parent;
        support.firePropertyChange("parentClassLoader", oldParentClassLoader,this.parentClassLoader);
    }
	@Override
    public Pipeline getPipeline() {
        return (this.pipeline);
    }
	@Override
    public Realm getRealm() {
        Lock l = realmLock.readLock();
        l.lock();
        try {
            if (realm != null)
                return (realm);
            if (parent != null)
                return (parent.getRealm());
            return null;
        } finally {
            l.unlock();
        }
    }
	protected Realm getRealmInternal() {
        Lock l = realmLock.readLock();
        l.lock();
        try {
            return realm;
        } finally {
            l.unlock();
        }
    }
	@Override
    public void setRealm(Realm realm) {
        Lock l = realmLock.writeLock();
        l.lock();
        try {
            Realm oldRealm = this.realm;
            if (oldRealm == realm)
                return;
            this.realm = realm;
            if (getState().isAvailable() && (oldRealm != null) &&
                (oldRealm instanceof Lifecycle)) {
                try {
                    ((Lifecycle) oldRealm).stop();
                } catch (LifecycleException e) {
                    log.error("ContainerBase.setRealm: stop: ", e);
                }
            }
            if (realm != null)
                realm.setContainer(this);
            if (getState().isAvailable() && (realm != null) &&
                (realm instanceof Lifecycle)) {
                try {
                    ((Lifecycle) realm).start();
                } catch (LifecycleException e) {
                    log.error("ContainerBase.setRealm: start: ", e);
                }
            }
            support.firePropertyChange("realm", oldRealm, this.realm);
        } finally {
            l.unlock();
        }
    }
	@Override
	public void addChild(Container child) {
		if (Globals.IS_SECURITY_ENABLED) {
            PrivilegedAction<Void> dp = new PrivilegedAddChild(child);
            AccessController.doPrivileged(dp);
        } else {
            addChildInternal(child);
        }
	}
	
	private void addChildInternal(Container child){
		//检查空指针
		Assert.notNull(child, "child不能为空");
		synchronized (children) {
			//查重
			Container _child = children.get(child.getName());
			if(_child == null){
				child.setParent(this);
				children.put(child.getName(), child);
			}else {
				throw new IllegalArgumentException("addChild:  Child name '" +child.getName() +"' is not unique");
			}
		}
		try {
            if ((getState().isAvailable() || LifecycleState.STARTING_PREP.equals(getState())) && startChildren) {
                child.start();
            }
        } catch (LifecycleException e) {
            log.error("ContainerBase.addChild: start: ", e);
            throw new IllegalStateException("ContainerBase.addChild: start: " + e);
        } finally {
            fireContainerEvent(ADD_CHILD_EVENT, child);
        }
	}
	@Override
    public void addContainerListener(ContainerListener listener) {
        listeners.add(listener);
    }
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    @Override
    public Container findChild(String name) {
        if (name == null) {
            return null;
        }
        synchronized (children) {
            return children.get(name);
        }
    }
    @Override
    public Container[] findChildren() {
        synchronized (children) {
            Container results[] = new Container[children.size()];
            return children.values().toArray(results);
        }
    }
    @Override
    public ContainerListener[] findContainerListeners() {
        ContainerListener[] results = new ContainerListener[0];
        return listeners.toArray(results);
    }
    @Override
    public void removeChild(Container child) {
        if (child == null) {
            return;
        }
        try {
            if (child.getState().isAvailable()) {
                child.stop();
            }
        } catch (LifecycleException e) {
            log.error("ContainerBase.removeChild: stop: ", e);
        }
        try {
            if (!LifecycleState.DESTROYING.equals(child.getState())) {
                child.destroy();
            }
        } catch (LifecycleException e) {
            log.error("ContainerBase.removeChild: destroy: ", e);
        }
        synchronized(children) {
            if (children.get(child.getName()) == null)
                return;
            children.remove(child.getName());
        }
        fireContainerEvent(REMOVE_CHILD_EVENT, child);
    }
    @Override
    public void removeContainerListener(ContainerListener listener) {
        listeners.remove(listener);
    }
    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
	/**
	 * 初始化线程池
	 */
    @Override
    protected void initInternal() throws LifecycleException {
    	BlockingQueue<Runnable> startStopQueue = new LinkedBlockingQueue<>();
        startStopExecutor = new ThreadPoolExecutor(
                getStartStopThreadsInternal(),
                getStartStopThreadsInternal(),
                10,
                TimeUnit.SECONDS,
                startStopQueue,
                new StartStopThreadFactory(getName() + "-startStop-"));
        startStopExecutor.allowCoreThreadTimeOut(true);
        super.initInternal();
    }
	
    @Override
    protected synchronized void startInternal() throws LifecycleException {
    	//开启我们的下属组件
    	Cluster cluster = getClusterInternal();
    	if(cluster instanceof Lifecycle){
    		((Lifecycle) cluster).start();
    	}
    	Realm realm = getRealmInternal();
    	if(realm instanceof Lifecycle){
    		((Lifecycle) realm).start();
    	}
    	//开启我们的子容器
    	Container[] children = findChildren();
    	List<Future<Void>> results = new ArrayList<>();
    	for(int i=0;i<children.length;i++){
    		results.add(startStopExecutor.submit(new StartChild(children[i])));
    	}
    	MultiThrowable multiThrowable = null;
        for (Future<Void> result : results) {
            try {
                result.get();
            } catch (Throwable e) {
                log.error(sm.getString("containerBase.threadedStartFailed"), e);
                if (multiThrowable == null) {
                    multiThrowable = new MultiThrowable();
                }
                multiThrowable.add(e);
            }
        }
        if (multiThrowable != null) {
            throw new LifecycleException(sm.getString("containerBase.threadedStartFailed"),multiThrowable.getThrowable());
        }
        if (pipeline instanceof Lifecycle) {
            ((Lifecycle) pipeline).start();
        }
        setState(LifecycleState.STARTING);
        threadStart();
    }
    /**
     * 开启后台线程，周期性检查会话超时问题
     */
    protected void threadStart() {
    	
    }
    @Override
    protected synchronized void stopInternal() throws LifecycleException {
    	
    }
	
    @Override
    protected void destroyInternal() throws LifecycleException {
    	
    }
    @Override
    public void logAccess(Request request, Response response, long time,boolean useDefault) {
        boolean logged = false;
        if (getAccessLog() != null) {
            getAccessLog().log(request, response, time);
            logged = true;
        }
        if (getParent() != null) {
            getParent().logAccess(request, response, time, (useDefault && !logged));
        }
    }

    @Override
    public AccessLog getAccessLog() {
        if (accessLogScanComplete) {
            return accessLog;
        }
        AccessLogAdapter adapter = null;
        Valve valves[] = getPipeline().getValves();
        for (Valve valve : valves) {
            if (valve instanceof AccessLog) {
                if (adapter == null) {
                    adapter = new AccessLogAdapter((AccessLog) valve);
                } else {
                    adapter.add((AccessLog) valve);
                }
            }
        }
        if (adapter != null) {
            accessLog = adapter;
        }
        accessLogScanComplete = true;
        return accessLog;
    }
    public synchronized void addValve(Valve valve) {
        pipeline.addValve(valve);
    }
    @Override
    public void backgroundProcess() {
    	
    }
    @Override
    public File getCatalinaBase() {
        if (parent == null) {
            return null;
        }
        return parent.getCatalinaBase();
    }
    @Override
    public File getCatalinaHome() {
        if (parent == null) {
            return null;
        }
        return parent.getCatalinaHome();
    }
    @Override
    public void fireContainerEvent(String type, Object data) {
        if (listeners.size() < 1) return;
        ContainerEvent event = new ContainerEvent(this, type, data);
        // Note for each uses an iterator internally so this is safe
        for (ContainerListener listener : listeners) {
            listener.containerEvent(event);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    protected class StartChild implements Callable<Void> {

        private Container child;

        public StartChild(Container child) {
            this.child = child;
        }

        @Override
        public Void call() throws LifecycleException {
            child.start();
            return null;
        }
    }
    protected class StopChild implements Callable<Void> {
    	
        private Container child;
        
        public StopChild(Container child) {
            this.child = child;
        }

        @Override
        public Void call() throws LifecycleException {
            if (child.getState().isAvailable()) {
                child.stop();
            }
            return null;
        }
    }
	protected class PrivilegedAddChild implements PrivilegedAction<Void>{
		
		private final Container child;
		public PrivilegedAddChild(Container child) {
			this.child = child;
		}
		@Override
		public Void run() {
			addChildInternal(child);
			return null;
		}
	}
}