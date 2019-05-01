package org.zk.retry.policy;

import java.util.Collections;

import org.zk.classify.Classifier;
import org.zk.classify.SubclassClassifier;
import org.zk.retry.RetryContext;
import org.zk.retry.RetryPolicy;
import org.zk.retry.context.SimpleRetryContext;

public class SimpleRetryPolicy implements RetryPolicy{
	
	private int maxAttempts;
	private Classifier<Throwable, Boolean> retryableClassifier=new SubclassClassifier<Throwable, Boolean>(Collections.singletonMap(Exception.class, Boolean.TRUE), Boolean.FALSE);
	
	public SimpleRetryPolicy(int maxAttempts) {
		this.maxAttempts = maxAttempts;
	}
	
	@Override
	public boolean canRetry(RetryContext context) {
		Throwable throwable = context.getLastThrowable();
		return (throwable==null || retryableClassifier.classify(throwable)) && context.getRetryCount() < maxAttempts;
	}
	
	@Override
	public RetryContext open() {
		return new SimpleRetryContext();
	}
	
	@Override
	public void close(RetryContext context) {
		
	}
	
	@Override
	public void registerThrowable(RetryContext context, Throwable throwable) {
		SimpleRetryContext simpleRetryContext=(SimpleRetryContext) context;
		simpleRetryContext.registerThrowable(throwable);
	}
}