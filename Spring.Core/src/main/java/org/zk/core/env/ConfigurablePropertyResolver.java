package org.zk.core.env;

import org.zk.core.convert.support.ConfigurableConversionService;

/**
 * 配置型属性解析器
 * 
 * 依赖于：属性源，转换服务
 * 
 * @author King 2019-5-29 16:06:35
 * 
 */
public interface ConfigurablePropertyResolver {
	
	ConfigurableConversionService getConversionService();
	void setConversionService(ConfigurableConversionService conversionService);
	
	//设置占位符的前后符号，值
	void setPlaceHolderPrefix(String placeHolderPrefix);
	void setPlaceHoldersuffix(String placeHoldersuffix);
	void setValueSeparator(String valueSeparator);
	
	void setIgnoreUnresolvableNestedPlaceholders(boolean ignoreUnresolvableNestedPlaceholders);
	void setRequiredProperties(String... requiredProperties);
	void validateRequiredProperties() throws MissingRequiredPropertyException;
}