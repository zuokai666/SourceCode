package org.zk.core.convert.converter;

/**
 * 用于向类型转换系统注册转换器
 * 
 * @author king
 * @date 2019-05-26 08:24:10
 *
 */
public interface ConverterRegistry {
	
	void addGenericConverter(GenericConverter converter);
	void addConverterFactory(ConverterFactory<?, ?> factory);
	void addConverter(Converter<?, ?> converter);
	<S,T> void addConverter(Class<S> sourceClass,Class<T> targetClass,Converter<S, T> converter);
	void removeConvertible(Class<?> sourceClass,Class<?> targetClass);
}