package org.zk.retry.context;

import org.springframework.core.AttributeAccessorSupport;
import org.zk.retry.RetryContext;

public abstract class RetryContextSupport extends AttributeAccessorSupport implements RetryContext{
	
	private static final long serialVersionUID = 8546606244727220303L;
	
	private volatile boolean terminate = false;
	private volatile int count;
	private volatile Throwable lastThrowable;
	
	public void setExhaustedOnly() {
		this.terminate=true;
	}
	public boolean isExhaustedOnly() {
		return terminate;
	}
	public int getRetryCount() {
		return count;
	}
	public Throwable getLastThrowable() {
		return lastThrowable;
	}
	
	public void registerThrowable(Throwable throwable) {
		this.lastThrowable = throwable;
		if (throwable != null)
			count++;
	}
}