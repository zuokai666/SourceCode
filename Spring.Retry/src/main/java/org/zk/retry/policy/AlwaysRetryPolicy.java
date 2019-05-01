package org.zk.retry.policy;

import org.zk.retry.RetryContext;

public class AlwaysRetryPolicy extends NeverRetryPolicy {
	
	public boolean canRetry(RetryContext context) {
		return true;
	}
}