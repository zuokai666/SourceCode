package org.zk.retry.backoff;

import java.util.Random;

import org.zk.retry.BackOffPolicy;

public class UniformRandomBackOffPolicy implements BackOffPolicy{
	
	private static final long DEFAULT_BACK_OFF_MIN_PERIOD = 500L;
	private static final long DEFAULT_BACK_OFF_MAX_PERIOD = 1500L;
	
	private volatile long minBackOffPeriod = DEFAULT_BACK_OFF_MIN_PERIOD;
	private volatile long maxBackOffPeriod = DEFAULT_BACK_OFF_MAX_PERIOD;
	
	private Random random = new Random(System.currentTimeMillis());
	
	@Override
	public void backOff() {
		long delta = maxBackOffPeriod==minBackOffPeriod ? 0 : random.nextInt((int) (maxBackOffPeriod - minBackOffPeriod));
		try {
			long v = minBackOffPeriod + delta;
			System.err.println("此次随机睡眠时间："+v);
			Thread.sleep(v);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}