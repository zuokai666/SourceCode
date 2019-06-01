package org.zk.core.annotation;

import org.zk.core.OrderComparator;

public class AnnotationAwareOrderComparator extends OrderComparator {
	
	@Override
	protected Integer findOrder(Object obj) {
		Integer interfaceOrder = super.findOrder(obj);
		if(interfaceOrder != null){
			return interfaceOrder;
		}
		
		return interfaceOrder;
	}
}