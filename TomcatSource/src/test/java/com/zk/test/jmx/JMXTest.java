package com.zk.test.jmx;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class JMXTest {
	
	public static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(JMXTest.class);
	
	public static void main(String[] args) throws Exception {
		MBeanServer server = ManagementFactory.getPlatformMBeanServer();
		ObjectName helloName = new ObjectName("jmxBean:name=hello");
		server.registerMBean(new Hello(), helloName);
		Thread.sleep(60*60*1000);
	}
}