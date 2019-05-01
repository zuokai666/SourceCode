package test.test;

import org.zk.retry.RecoveryCallback;
import org.zk.retry.RetryContext;

public class MeRecoveryCallback implements RecoveryCallback<String>{
	
	public String recover(RetryContext context) throws Exception {
		System.out.println("recover");
		return "recover";
	}
}