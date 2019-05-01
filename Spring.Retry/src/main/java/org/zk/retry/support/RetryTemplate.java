package org.zk.retry.support;

import org.zk.retry.BackOffPolicy;
import org.zk.retry.RecoveryCallback;
import org.zk.retry.RetryCallback;
import org.zk.retry.RetryContext;
import org.zk.retry.RetryException;
import org.zk.retry.RetryOperations;
import org.zk.retry.RetryPolicy;
import org.zk.retry.backoff.FixedBackOffPolicy;
import org.zk.retry.policy.SimpleRetryPolicy;

public class RetryTemplate implements RetryOperations{
	
	private BackOffPolicy backOffPolicy=new FixedBackOffPolicy();
	
	private RetryPolicy retryPolicy=new SimpleRetryPolicy(3);
	
	public void setBackOffPolicy(BackOffPolicy backOffPolicy) {
		this.backOffPolicy = backOffPolicy;
	}
	
	public void setRetryPolicy(RetryPolicy retryPolicy) {
		this.retryPolicy = retryPolicy;
	}
	
	@Override
	public <T, E extends Throwable> T execute(RetryCallback<T, E> retryCallback) throws E, Exception {
		return doExecute(retryCallback, null);
	}
	
	@Override
	public <T, E extends Throwable> T execute(RetryCallback<T, E> retryCallback,RecoveryCallback<T> recoveryCallback) throws E,Exception {
		return doExecute(retryCallback, recoveryCallback);
	}
	
	protected <T, E extends Throwable> T doExecute(RetryCallback<T, E> retryCallback,RecoveryCallback<T> recoveryCallback) throws E,Exception {
		RetryContext retryContext=retryPolicy.open();
		Throwable lastException = null;
		try {
			doOpenInterceptors(retryCallback, retryContext);
			
			while(retryPolicy.canRetry(retryContext) && !retryContext.isExhaustedOnly()){
				try {
					System.out.println("Retry: count=" + retryContext.getRetryCount());
					lastException= null;
					return retryCallback.doWithRetry(retryContext);
				} catch (Throwable e) {
					lastException=e;
					try {
						retryPolicy.registerThrowable(retryContext, e);
					} finally {
						doOnErrorInterceptors(retryCallback, retryContext, e);
					}
					if(retryPolicy.canRetry(retryContext) && !retryContext.isExhaustedOnly()){
						backOffPolicy.backOff();
					}
				}
			}
			return handleRetryExhausted(recoveryCallback, retryContext);
		} finally {
			close(retryPolicy, retryContext);
			doCloseInterceptors(retryCallback, retryContext, lastException);
		}
	}
	
	private void close(RetryPolicy retryPolicy, RetryContext retryContext) {
		retryPolicy.close(retryContext);
		retryContext.setAttribute(RetryContext.closed, true);
	}
	
	private <T> T handleRetryExhausted(RecoveryCallback<T> recoveryCallback, RetryContext retryContext) throws Exception {
		retryContext.setAttribute(RetryContext.exhausted, true);
		if(recoveryCallback != null){
			T recovered=recoveryCallback.recover(retryContext);
			retryContext.setAttribute(RetryContext.recovered, true);
			return recovered;
		}
		throw new RetryException("not found RecoveryCallback");
	}
	
	private <T, E extends Throwable> void doOnErrorInterceptors(RetryCallback<T, E> retryCallback, RetryContext retryContext, Throwable e) {
		System.err.println("doOnErrorInterceptors");
	}
	private <T, E extends Throwable> boolean doOpenInterceptors(RetryCallback<T, E> retryCallback, RetryContext retryContext) {
		System.err.println("doOpenInterceptors");
		return true;
	}
	private <T, E extends Throwable> void doCloseInterceptors(RetryCallback<T, E> retryCallback, RetryContext retryContext,Throwable lastException) {
		System.err.println("doCloseInterceptors");
	}
}