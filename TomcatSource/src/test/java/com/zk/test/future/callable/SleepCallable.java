package com.zk.test.future.callable;

import java.util.Random;
import java.util.concurrent.Callable;

public class SleepCallable implements Callable<String>{
	
	private int seconds = 0;
	
	public SleepCallable() {
	}
	
	public SleepCallable(int seconds) {
		this.seconds = seconds;
	}
	
	@Override
	public String call() throws Exception {
		if(seconds == 0){
			seconds = new Random().nextInt(10) + 1;
		}
		Thread.sleep(1000 * seconds);
		return Thread.currentThread().getName() + "；seconds=" + seconds;
	}
}