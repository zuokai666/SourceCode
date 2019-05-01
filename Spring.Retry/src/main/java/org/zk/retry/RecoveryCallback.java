package org.zk.retry;

public interface RecoveryCallback<T> {
	
	T recover(RetryContext context) throws Exception;
}