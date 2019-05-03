package com.zk.test.jmx;

public class Hello implements HelloMBean {
	
	public static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Hello.class);
	
	private String name;
	private int age;
	
	@Override
	public void setName(String name) {
		log.info("设置name={}",name);
		this.name = name;
	}

	@Override
	public String getName() {
		log.info("获得name={}",name);
		return name;
	}

	@Override
	public void setAge(int age) {
		log.info("设置age={}",age);
		this.age = age;
	}
	
	@Override
	public int getAge() {
		log.info("获得age={}",age);
		return age;
	}
}