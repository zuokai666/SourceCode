package org.zk.retry;

public class RetryException extends RuntimeException{
	
	private static final long serialVersionUID = -4887838692784637069L;
	
	public RetryException(String message) {
		super(message);
	}
}