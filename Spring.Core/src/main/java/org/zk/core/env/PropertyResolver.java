package org.zk.core.env;

/**
 * 属性解析器
 * 
 * 
 * @author King 2019-5-29 16:06:35
 * 
 */
public interface PropertyResolver {
	
	boolean containsProperty(String key);
	
	String getProperty(String key);
	String getProperty(String key, String defaultValue);
	String getRequiredProperty(String key) throws IllegalStateException;
	
	<T> T getProperty(String key, Class<T> targetType);
	<T> T getProperty(String key, Class<T> targetType, T defaultValue);
	<T> T getRequiredProperty(String key, Class<T> targetType) throws IllegalStateException;
	
	String resolvePlaceHolders(String text);
	String resolveRequiredPlaceHolders(String text) throws IllegalArgumentException;
}