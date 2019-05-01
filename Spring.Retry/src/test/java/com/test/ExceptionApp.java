package com.test;

import java.io.IOException;
import java.net.BindException;
import java.net.ConnectException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.classify.SubclassClassifier;

public class ExceptionApp {
	
	public static void main(String[] args) {
		Map<Class<? extends Throwable>, Boolean> classes = new TreeMap<Class<? extends Throwable>, Boolean>(new ClassComparator());
		classes.put(Exception.class, Boolean.TRUE);
		classes.put(IOException.class, Boolean.FALSE);
		classes.put(SocketException.class, Boolean.TRUE);
		classes.put(BindException.class, Boolean.FALSE);
		System.err.println(classes.toString());

		Map<Class<? extends Throwable>, Boolean> map=new HashMap<Class<? extends Throwable>, Boolean>();
		map.put(Exception.class, Boolean.TRUE);
		map.put(IOException.class, Boolean.FALSE);
		map.put(SocketException.class, Boolean.TRUE);
		map.put(BindException.class, Boolean.FALSE);
		
		SubclassClassifier<Throwable, Boolean> retryableClassifier = new SubclassClassifier<Throwable, Boolean>(map, false);
		
		Boolean v1=retryableClassifier.classify(new ConnectException());
		Boolean v2=retryableClassifier.classify(new IOException());
		System.err.println(v1);
		System.err.println(v2);
	}
}