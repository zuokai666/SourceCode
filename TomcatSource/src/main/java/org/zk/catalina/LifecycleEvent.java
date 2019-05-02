package org.zk.catalina;

import java.util.EventObject;

public final class LifecycleEvent extends EventObject{
	
	private static final long serialVersionUID = 8314345512181486941L;
	
	public LifecycleEvent(Object source) {
		super(source);
	}
}