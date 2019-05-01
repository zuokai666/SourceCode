package org.zk.retry;

import org.springframework.core.AttributeAccessor;

public interface RetryContext extends AttributeAccessor{
	
	String name="context.name";
	String state_key="context.state";
	String closed="context.closed";
	String recovered="context.recovered";
	String exhausted="context.exhausted";
	
	void setExhaustedOnly();
	
	boolean isExhaustedOnly();
	
	int getRetryCount();
	
	Throwable getLastThrowable();
}