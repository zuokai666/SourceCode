package com.tomcat.util.threads;

public class TaskThread extends Thread {
	
	private final long creationTime;
	
	public TaskThread(ThreadGroup group,String name,Runnable target){
		super(group, new WrappingRunnable(target), name);
		this.creationTime = System.currentTimeMillis();
	}
	
	public TaskThread(ThreadGroup group,String name,Runnable target,long stackSize){
		super(group, new WrappingRunnable(target), name, stackSize);
		this.creationTime = System.currentTimeMillis();
	}
	
	public final long getCreationTime() {
		return creationTime;
	}
}