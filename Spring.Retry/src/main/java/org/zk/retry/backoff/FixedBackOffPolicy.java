package org.zk.retry.backoff;

import org.zk.retry.BackOffPolicy;

public class FixedBackOffPolicy implements BackOffPolicy{

	private static final long DEFAULT_BACK_OFF_PERIOD = 1000L;
	
	private volatile long backOffPeriod = DEFAULT_BACK_OFF_PERIOD;
	
	@Override
	public void backOff(){
		try {
			Thread.sleep(backOffPeriod);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}