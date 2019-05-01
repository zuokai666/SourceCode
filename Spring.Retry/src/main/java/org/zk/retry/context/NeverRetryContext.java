package org.zk.retry.context;

public class NeverRetryContext extends RetryContextSupport {
	
	private static final long serialVersionUID = -3446208052983719364L;
	
	private boolean finished = false;
	
	public boolean isFinished() {
		return finished;
	}
	
	public void setFinished() {
		this.finished = true;
	}
}