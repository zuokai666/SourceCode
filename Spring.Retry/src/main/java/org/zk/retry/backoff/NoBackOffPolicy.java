package org.zk.retry.backoff;

import org.zk.retry.BackOffPolicy;

public class NoBackOffPolicy implements BackOffPolicy{
	
	@Override
	public void backOff() {
		
	}
}