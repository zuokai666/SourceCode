package org.zk.catalina.util;

import org.zk.tomcat.util.res.StringManager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.zk.tomcat.util.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.zk.catalina.Lifecycle;
import org.zk.catalina.LifecycleException;
import org.zk.catalina.LifecycleState;
import org.zk.catalina.event.LifecycleEvent;
import org.zk.catalina.listener.LifecycleListener;

/**
 * 生命周期的抽象基类，定义了执行方法的骨架
 * 
 * @author king
 * @date 2019-05-03 13:29:40
 * 
 */
public abstract class LifecycleBase implements Lifecycle{
	
	public static final Logger log = LoggerFactory.getLogger(LifecycleBase.class);
	
	public static final StringManager sm = StringManager.getManager(LifecycleBase.class);
	public final List<LifecycleListener> lifecycleListeners = new CopyOnWriteArrayList<LifecycleListener>();
	//初始化当前组件的状态
	public volatile LifecycleState state = LifecycleState.NEW;
	
    @Override
    public void addLifecycleListener(LifecycleListener listener) {
        lifecycleListeners.add(listener);
    }
    @Override
    public LifecycleListener[] findLifecycleListeners() {
        return lifecycleListeners.toArray(new LifecycleListener[0]);
    }
    @Override
    public void removeLifecycleListener(LifecycleListener listener) {
        lifecycleListeners.remove(listener);
    }
    
    /**
     * 当生命周期事件发生后，应该调用此方法通知观察者们
     * 采用轮询方式，也就是for循环，应该会转为迭代器，迭代器模式
     */
    protected void fireLifecycleEvent(String type,Object data){
    	LifecycleEvent event = new LifecycleEvent(this, type, data);
    	for(LifecycleListener lifecycleListener:lifecycleListeners){
    		lifecycleListener.lifecycleEvent(event);
    	}
    }
    /**
     * 1.首先检查当前状态是不是new状态，如果是，接着走，否则就是无效的转换，应该抛出异常
     * 2.设置状态为初始化中
     * 3.调用延迟到子类的内部初始化方法
     * 4.设置状态为初始化完毕
     * 5.从2-4需要捕捉异常，如果异常，异常拦截器处理，然后设置状态为失败，抛出生命周期异常
     */
    @Override
    public final synchronized void init() throws LifecycleException {
    	if(!state.equals(LifecycleState.NEW)){
    		invalidTransition(Lifecycle.BEFORE_INIT_EVENT);
    	}
    	try {
            setStateInternal(LifecycleState.INITIALIZING, null, false);
            initInternal();
            setStateInternal(LifecycleState.INITIALIZED, null, false);
        } catch (Throwable t) {
            ExceptionUtils.handleThrowable(t);
            setStateInternal(LifecycleState.FAILED, null, false);
            throw new LifecycleException(sm.getString("lifecycleBase.initFail",toString()), t);
        }
    }
    
    protected abstract void initInternal() throws LifecycleException;
    
    @Override
    public final synchronized void start() throws LifecycleException {
    	if(state == LifecycleState.STARTING_PREP || state == LifecycleState.STARTING || state == LifecycleState.STARTED){
    		if(log.isDebugEnabled()){
    			Exception exception = new LifecycleException();
    			log.debug(sm.getString("lifecycleBase.alreadyStarted",toString()), exception);
    		}else {
       			log.info(sm.getString("lifecycleBase.alreadyStarted",toString()));
			}
    		return;
    	}
    	if(state == LifecycleState.NEW){
    		init();
    	}else if(state == LifecycleState.FAILED){
    		stop();
    	}else if (!state.equals(LifecycleState.INITIALIZED) && !state.equals(LifecycleState.STOPPED)) {
            invalidTransition(Lifecycle.BEFORE_START_EVENT);
        }
    	
    }
    
    /**
     * 更新组件的状态
     * 调用这个方法也将会自动的触动生命周期事件
     * 自动检查状态的改变是否正确
     */
    protected synchronized void setState(LifecycleState state, Object data)throws LifecycleException {
        setStateInternal(state, data, true);
    }
    
    /**
     * 必须是私有的，防止子类任意修改
     */
    private synchronized void setStateInternal(LifecycleState state,Object data, boolean check) throws LifecycleException {
    	LifecycleState oldState = this.state;
    	LifecycleState newState = state;
    	if (log.isDebugEnabled()) {
            log.debug(sm.getString("lifecycleBase.setState", this, newState));
        }
    	if(check){
    		if(state == null){
    			invalidTransition("null");
    			//NPE 		难以理解 		2019-05-03 19:19:06
    			return;
    		}
    		//所有的方法都可以赚到Failed
    		if(newState == LifecycleState.FAILED){
    			
    		}else if(oldState == LifecycleState.STARTING_PREP && newState == LifecycleState.STARTING){
    			
    		}else if(oldState == LifecycleState.STOPPING_PREP && newState == LifecycleState.STOPPING){
    			
    		}else if(oldState == LifecycleState.FAILED && newState == LifecycleState.STOPPING){
    			
    		}else {
				invalidTransition(newState.name());
			}
    	}
    	this.state = newState;
    	String lifecycleEvent = state.getLifecycleEvent();
    	if(lifecycleEvent != null){
    		fireLifecycleEvent(lifecycleEvent, data);
    	}
    }
    
	private void invalidTransition(String type) throws LifecycleException {
		String msg = sm.getString("lifecycleBase.invalidTransition", type, toString(), state);
        throw new LifecycleException(msg);
	}
}