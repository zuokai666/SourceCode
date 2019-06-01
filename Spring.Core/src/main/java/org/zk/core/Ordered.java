package org.zk.core;

/**
 * 有了优先级顺序，就要有维护顺序的程序，要不然也没啥用，涉及到加载，运行，都会涉及到顺序
 * 
 * @author king
 * @date 2019-06-01 07:32:26
 *
 */
public interface Ordered {
	
	int highest_precedence = Integer.MIN_VALUE;
	int lowest_precedence = Integer.MAX_VALUE;
	
	int getOrder();
}