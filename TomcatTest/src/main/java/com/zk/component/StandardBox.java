package com.zk.component;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.ContainerBase;

public class StandardBox extends ContainerBase{

	public StandardBox() {
		this.pipeline.setBasic(new StandardBoxValve());
	}
	
	@Override
	protected String getObjectNameKeyProperties() {
		return null;
	}
	
	@Override
	protected synchronized void startInternal() throws LifecycleException {
		getPipeline().addValve(new FirstValve());
		getPipeline().addValve(new SecondValve());
		super.startInternal();
	}
}