package com.zk.test.jmx;

public interface HelloMBean {
	
	void setName(String name);
	String getName();
	
	void setAge(int age);
	int getAge();
}