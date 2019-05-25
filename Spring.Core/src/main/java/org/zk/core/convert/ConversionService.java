package org.zk.core.convert;

/**
 * 类型转换系统的入口
 * 
 * @author King
 *
 */
public interface ConversionService {
	
	boolean canConvert();
	<T> T convert(Object source,Class<T> targetType);
}