package com.zk.component;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;

public class StandardBoxValve extends ValveBase{

	public void invoke(Request request, Response response) throws IOException, ServletException {
		System.err.println(2);
	}
}