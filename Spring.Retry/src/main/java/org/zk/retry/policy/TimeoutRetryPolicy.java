package org.zk.retry.policy;

import org.zk.retry.RetryContext;
import org.zk.retry.RetryPolicy;
import org.zk.retry.context.TimeoutRetryContext;

public class TimeoutRetryPolicy implements RetryPolicy {
	
	public static final long DEFAULT_TIMEOUT = 1000 * 5;

	private long timeout = DEFAULT_TIMEOUT;

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
	
	/**
	 * The value of the timeout.
	 * 
	 * @return the timeout in milliseconds
	 */
	public long getTimeout() {
		return timeout;
	}

	/**
	 * Only permits a retry if the timeout has not expired. Does not check the
	 * exception at all.
	 * 
	 * @see org.springframework.retry.RetryPolicy#canRetry(org.springframework.retry.RetryContext)
	 */
	public boolean canRetry(RetryContext context) {
		return ((TimeoutRetryContext) context).isAlive();
	}

	public void close(RetryContext context) {
	}

	public RetryContext open() {
		return new TimeoutRetryContext(timeout);
	}

	public void registerThrowable(RetryContext context, Throwable throwable) {
		((TimeoutRetryContext) context).registerThrowable(throwable);
	}
}