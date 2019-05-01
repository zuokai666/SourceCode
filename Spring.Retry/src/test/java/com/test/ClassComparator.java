package com.test;

import java.util.Comparator;

public class ClassComparator implements Comparator<Class<?>> {
	
	public int compare(Class<?> arg0, Class<?> arg1) {
		if (arg0.isAssignableFrom(arg1)) {
			return 1;
		}
		return -1;
	}
}