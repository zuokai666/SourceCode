package com.zk.component;

import java.util.Set;

import org.apache.tomcat.util.net.AbstractEndpoint.Handler;
import org.apache.tomcat.util.net.SocketEvent;
import org.apache.tomcat.util.net.SocketWrapperBase;

public class ConnectionHandler<S> implements Handler<S>{

	public SocketState process(SocketWrapperBase<S> socket,SocketEvent status) {
		System.err.println("handler.process");
		return null;
	}

	public Object getGlobal() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<S> getOpenSockets() {
		// TODO Auto-generated method stub
		return null;
	}

	public void release(SocketWrapperBase<S> socketWrapper) {
		// TODO Auto-generated method stub
		
	}

	public void pause() {
		// TODO Auto-generated method stub
		
	}

	public void recycle() {
		// TODO Auto-generated method stub
		
	}

}
