package com.king.discovery;

import java.util.TimerTask;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 时间监督任务
 * 策略：延时执行任务，如果遇到超时任务，则在不超过最大
 */
public class TimedSupervisorTask extends TimerTask{
	
	private static final Logger log = LoggerFactory.getLogger(TimedSupervisorTask.class);
	
	private final ScheduledExecutorService scheduler;
	private final ThreadPoolExecutor executor;
	private final long timeoutMillis;
	private final Runnable task;
	private final AtomicLong delay;
	private final long maxDelay;
	
	public TimedSupervisorTask(String name, ScheduledExecutorService scheduler, ThreadPoolExecutor executor,
			int timeout, TimeUnit timeUnit, int expBackOffBound, Runnable task) {
		this.scheduler = scheduler;
		this.executor = executor;
		this.timeoutMillis = timeUnit.toMillis(timeout);
		this.task = task;
		this.delay = new AtomicLong(timeoutMillis);
		this.maxDelay = timeoutMillis * expBackOffBound;
	}
	
	@Override
	public void run() {
		Future<?> future = null;
		try {
			future = executor.submit(task);
			future.get(timeoutMillis, TimeUnit.MICROSECONDS);//阻塞直到成功或超时
			delay.set(timeoutMillis);
		} catch (TimeoutException e) {
			log.warn("task supervisor timed out", e);
			long currentDelay = delay.get();
			long newDelay = Math.min(maxDelay, currentDelay * 2);//如果超时，将时间翻倍
			delay.compareAndSet(currentDelay, newDelay);
		} catch (RejectedExecutionException e) {
			log.warn("task supervisor reject the task", e);
		} catch (Throwable e) {
			log.warn("task supervisor throw an exception", e);
		} finally {
			if(future != null){
				future.cancel(true);
			}
			if(!scheduler.isShutdown()){
				scheduler.schedule(this, delay.get(), TimeUnit.MICROSECONDS);
			}
		}
	}
}