package org.zk.core.convert.converter;

/**
 * S:source源
 * T:target目标
 * 
 * @author king
 * @date 2019-05-25 22:00:30
 *
 */
public interface Converter<S,T> {
	
	T convert(S source);
}