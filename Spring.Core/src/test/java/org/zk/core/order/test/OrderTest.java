package org.zk.core.order.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.OrderComparator;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.annotation.OrderUtils;
import org.zk.core.order.model.User;
import org.zk.core.order.model.User2;

/**
 * 问：测试spring.@order与javax.@priority哪个优先级高
 * 答：先order后priority
 * 
 * @author king
 * @date 2019-06-01 08:00:02
 *
 */
public class OrderTest {
	
	public static void main(String[] args) {
		System.err.println(OrderUtils.getOrder(User.class));
		
		List<Object> list = new ArrayList<>();
		list.add(new User());
		list.add(new User2());
		
		OrderComparator.sort(list);
		
		AnnotationAwareOrderComparator.sort(list);
	}
}