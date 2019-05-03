package com.zk.test;

import org.zk.catalina.LifecycleException;
import org.zk.catalina.LifecycleState;
import org.zk.catalina.SingleUse;
import org.zk.catalina.event.LifecycleEvent;
import org.zk.catalina.listener.LifecycleListener;
import org.zk.catalina.util.LifecycleBase;

/**
 * 测试一个完整生命周期中状态的改变
 * 
 * 
 * @author king
 * @date 2019-05-03 22:06:30
 *
 */
public class LifecycleTest extends LifecycleBase implements SingleUse,LifecycleListener{
	
	public static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LifecycleTest.class);
	
	public LifecycleTest() {
		addLifecycleListener(this);
	}
	
	@Override
	protected void initInternal() throws LifecycleException {
		
	}

	@Override
	protected void startInternal() throws LifecycleException {
		setState(LifecycleState.STARTING, null);
	}

	@Override
	protected void stopInternal() throws LifecycleException {
		setState(LifecycleState.STOPPING, null);
	}

	@Override
	protected void destroyInternal() throws LifecycleException {
		
	}
	@Override
	public String toString() {
		return "LifecycleTest";
	}
	public static void main(String[] args) {
		LifecycleTest lifecycleTest = new LifecycleTest();
		try {
			lifecycleTest.start();
			lifecycleTest.stop();
		} catch (LifecycleException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void lifecycleEvent(LifecycleEvent lifecycleEvent) {
		log.info(lifecycleEvent.getType());
	}
}