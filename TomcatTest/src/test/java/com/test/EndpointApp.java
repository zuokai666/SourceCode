package com.test;

import org.apache.tomcat.util.net.NioEndpoint;

public class EndpointApp {

	public static void main(String[] args) {
		NioEndpoint nioEndpoint = new NioEndpoint();
		nioEndpoint.setPort(8080);
		try {
			nioEndpoint.start();
			while(true){
				Thread.sleep(10 * 1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}