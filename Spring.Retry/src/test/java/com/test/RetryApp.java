package com.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

public class RetryApp {
	
	public static void main(String[] args) throws Throwable {
		Map<Class<? extends Throwable>, Boolean> map=new HashMap<Class<? extends Throwable>, Boolean>();
//		map.put(Exception.class, Boolean.TRUE);
		map.put(IOException.class, Boolean.TRUE);
		
		RetryTemplate retryTemplate=new RetryTemplate();
		retryTemplate.setRetryPolicy(new SimpleRetryPolicy(3,map,true,Boolean.FALSE));
		retryTemplate.setBackOffPolicy(new FixedBackOffPolicy());
		retryTemplate.execute(new MeRetryCallback(), new MeRecoveryCallback());
	}
	
	static class MeRetryCallback implements RetryCallback<Object, Throwable>{
		
		public Object doWithRetry(RetryContext context) throws Throwable {
			System.err.println(1);
			int i = 9;
			if(i == 9){
				throw new RuntimeException("i==9",new IOException());
//				throw new IOException("i==9");
			}
			return "retry";
		}
	}
	
	static class MeRecoveryCallback implements RecoveryCallback<Object>{
		
		public Object recover(RetryContext context) throws Exception {
			System.out.println(2);
			return "recover";
		}
	}
}