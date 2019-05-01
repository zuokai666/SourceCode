package test.test;

import org.zk.retry.backoff.FixedBackOffPolicy;
import org.zk.retry.backoff.UniformRandomBackOffPolicy;
import org.zk.retry.policy.AlwaysRetryPolicy;
import org.zk.retry.policy.NeverRetryPolicy;
import org.zk.retry.policy.SimpleRetryPolicy;
import org.zk.retry.support.RetryTemplate;

public class MeRetryApp {
	
	public static void main1(String[] args) throws Throwable {
		RetryTemplate retryTemplate=new RetryTemplate();
		retryTemplate.setBackOffPolicy(new FixedBackOffPolicy());
		retryTemplate.setRetryPolicy(new SimpleRetryPolicy(3));
		String result=retryTemplate.execute(new MeRetryCallback(), new MeRecoveryCallback());
		System.err.println("retryTemplate���ؽ��:"+result);
	}
	
	public static void main2(String[] args) throws Throwable {
		RetryTemplate retryTemplate=new RetryTemplate();
		retryTemplate.setBackOffPolicy(new FixedBackOffPolicy());
		retryTemplate.setRetryPolicy(new NeverRetryPolicy());
		String result=retryTemplate.execute(new MeRetryCallback(), new MeRecoveryCallback());
		System.err.println("retryTemplate���ؽ��:"+result);
	}
	
	public static void main3(String[] args) throws Throwable {
		RetryTemplate retryTemplate=new RetryTemplate();
		retryTemplate.setBackOffPolicy(new FixedBackOffPolicy());
		retryTemplate.setRetryPolicy(new AlwaysRetryPolicy());
		String result=retryTemplate.execute(new MeRetryCallback(), new MeRecoveryCallback());
		System.err.println("retryTemplate���ؽ��:"+result);
	}
	
	public static void main(String[] args) throws Throwable {
		RetryTemplate retryTemplate=new RetryTemplate();
		retryTemplate.setBackOffPolicy(new UniformRandomBackOffPolicy());
		retryTemplate.setRetryPolicy(new SimpleRetryPolicy(3));
		String result=retryTemplate.execute(new MeRetryCallback(), new MeRecoveryCallback());
		System.err.println("retryTemplate���ؽ��:"+result);
	}
}