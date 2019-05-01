package org.zk.retry;

public interface RetryOperations {
	
	<T,E extends Throwable> T execute(RetryCallback<T, E> retryCallback) throws E,Exception;
	
	<T,E extends Throwable> T execute(RetryCallback<T, E> retryCallback, 
			RecoveryCallback<T> recoveryCallback) throws E,Exception;
}