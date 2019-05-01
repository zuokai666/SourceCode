package org.zk.retry.policy;

import org.zk.retry.RetryContext;
import org.zk.retry.RetryPolicy;
import org.zk.retry.context.NeverRetryContext;

public class NeverRetryPolicy implements RetryPolicy{
	
	@Override
	public boolean canRetry(RetryContext context) {
		return !((NeverRetryContext)context).isFinished();
	}
	
	@Override
	public RetryContext open() {
		return new NeverRetryContext();
	}

	@Override
	public void close(RetryContext context) {
		//no-op
	}
	
	@Override
	public void registerThrowable(RetryContext context, Throwable throwable) {
		((NeverRetryContext) context).setFinished();
		((NeverRetryContext) context).registerThrowable(throwable);
	}
}