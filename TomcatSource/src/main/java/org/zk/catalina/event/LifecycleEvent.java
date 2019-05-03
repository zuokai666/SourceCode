package org.zk.catalina.event;

import java.util.EventObject;

import org.zk.catalina.Lifecycle;

public final class LifecycleEvent extends EventObject{
	
	private static final long serialVersionUID = 8314345512181486941L;
	
	private String type;
	private Object data;
	
	public LifecycleEvent(Lifecycle component,String type,Object data){
		super(component);
		this.type = type;
		this.data = data;
	}
	
	public String getType() {
		return type;
	}
	
	public Object getData() {
		return data;
	}
	
	public Lifecycle getLifecycle() {
		return (Lifecycle)getSource();
	}
}