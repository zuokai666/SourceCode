package com.logback.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task implements Runnable{
	
	private Logger log = LoggerFactory.getLogger(Task.class);
	
	@Override
	public void run() {
		while(true){
			if(log.isDebugEnabled()){
				log.debug("调试输出:{}", log.getClass().getName());
			}
			if(log.isInfoEnabled()){
				log.info("信息输出:{}", log.getClass().getName());
			}
			try {
				Thread.sleep(3 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}