package org.zk.retry;

public interface RetryPolicy {
	
	boolean canRetry(RetryContext context);
	
	RetryContext open();
	
	void close(RetryContext context);
	
	void registerThrowable(RetryContext context,Throwable throwable);
}