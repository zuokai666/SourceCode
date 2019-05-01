package org.zk.retry;

public interface BackOffPolicy {
	
	void backOff();
}