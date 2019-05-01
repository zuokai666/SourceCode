package test.test;

import org.zk.retry.RetryCallback;
import org.zk.retry.RetryContext;

public class MeRetryCallback implements RetryCallback<String, Throwable>{
	
	public String doWithRetry(RetryContext context) throws Throwable {
		System.err.println("retry");
		int i = 9;
		if(i == 9){
			throw new RuntimeException("i==9");
//			throw new InternalError("i==9");
		}
		return "retry";
	}
}