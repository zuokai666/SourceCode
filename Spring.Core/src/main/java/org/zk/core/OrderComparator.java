package org.zk.core;

import java.util.Comparator;

public class OrderComparator implements Comparator<Object>{
	
	@Override
	public int compare(Object o1, Object o2) {
		return 0;
	}
	
	/**
	 * 默认实现只会检测Ordered接口，可以被子类覆写
	 * 
	 * @param obj
	 * @return
	 */
	protected Integer findOrder(Object obj){
		return (obj instanceof Ordered) ? ((Ordered)obj).getOrder() : null;
	}
}