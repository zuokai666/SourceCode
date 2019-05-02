package com.test;

import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;

public class MyLifecycleListener implements LifecycleListener{
	
	public void lifecycleEvent(LifecycleEvent event) {
		System.err.println(event.getType());
	}
}