package com.test;

import org.apache.catalina.connector.Connector;

public class ConnectorApp {

	public static void main(String[] args) throws Exception {
		Connector connector = new Connector("HTTP/1.1");
		connector.setPort(8080);
		connector.start();
		
		while(true){
			Thread.sleep(10 * 1000);
		}
	}
}