package org.zk.catalina.event;

import java.util.EventObject;

public final class ContainerEvent extends EventObject {
	
	private static final long serialVersionUID = 5346587424437907152L;

	public ContainerEvent(Object source) {
		super(source);
	}
}