package com.test;

import com.tomcat.util.threads.Acceptor;
import com.tomcat.util.threads.Poller;

public class BlockApp {
	
	public static final Thread acceptor = new Thread(new Acceptor(), "Acceptor-1");
	public static final Thread poller = new Thread(new Poller(), "Poller-1");
	
	public static void main(String[] args) {
		acceptor.setDaemon(true);
		acceptor.start();
		
		poller.setDaemon(true);
		poller.start();
	}
}