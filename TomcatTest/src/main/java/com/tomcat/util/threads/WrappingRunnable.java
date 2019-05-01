package com.tomcat.util.threads;

import org.apache.tomcat.util.threads.StopPooledThreadException;

public class WrappingRunnable implements Runnable {

	private Runnable target;
	
	public WrappingRunnable(Runnable target){
		this.target = target;
	}
	
	public void run() {
		try {
			target.run();
        } catch(StopPooledThreadException exc) {
        	exc.printStackTrace();
        }
	}
}