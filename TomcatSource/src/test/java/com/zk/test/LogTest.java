package com.zk.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {
	
	public static final Logger log = LoggerFactory.getLogger(LogTest.class);
	
	public static void main(String[] args) {
		log.debug("debug");
		log.error("error");
	}
}