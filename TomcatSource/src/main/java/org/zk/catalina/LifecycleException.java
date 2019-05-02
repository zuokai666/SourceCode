package org.zk.catalina;

/**
 * 有关生命周期问题的通用异常
 * 
 * @author king
 * @date 2019-05-02 16:45:51
 * 
 */
public final class LifecycleException extends Exception{
	
	private static final long serialVersionUID = 732262798017682156L;
	
	public LifecycleException() {
		super();
	}
	
	public LifecycleException(String message){
		super(message);
	}
	
	public LifecycleException(Throwable throwable){
		super(throwable);
	}
	
	public LifecycleException(String message,Throwable throwable){
		super(message, throwable);
	}
}