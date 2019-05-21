package org.zk.catalina;

/**
 * 一个特定的Context的Session的管理器
 * 
 * @author king
 * @date 2019-05-03 11:56:02
 *
 */
public interface Manager {
	
	public Context getContext();
	public void setContext(Context context);
	public SessionIdGenerator getSessionIdGenerator();
	public void setSessionIdGenerator(SessionIdGenerator sessionIdGenerator);
	public long getSessionCounter();
	
	//... ...
}