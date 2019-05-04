package com.zk.test.future.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Callable用于产生结果，Future用于获取结果
 * 
 * 
 * @author king
 * @date 2019-05-04 21:45:43
 */
public class FcTest {
	
	public static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(FcTest.class);
	
	public static void main(String[] args) {
		List<Future<String>> results = new ArrayList<>();
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for(int i=5;i>=1;i--){
			Future<String> future = executor.submit(new SleepCallable(i));
			results.add(future);
		}
		for(int i=0;i<results.size();i++){
			Future<String> future = results.get(i);
			try {
				String result = future.get(3,TimeUnit.SECONDS);
				log.info(result);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		executor.shutdown();
	}
}