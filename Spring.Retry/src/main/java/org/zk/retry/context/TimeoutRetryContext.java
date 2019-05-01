package org.zk.retry.context;

public class TimeoutRetryContext extends RetryContextSupport {
	
	private static final long serialVersionUID = -8466121616439695895L;

	private long timeout;

	private long start;

	public TimeoutRetryContext(long timeout) {
		this.start = System.currentTimeMillis();
		this.timeout = timeout;
	}

	public boolean isAlive() {
		return (System.currentTimeMillis() - start) <= timeout;
	}
}