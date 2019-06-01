package com.zk.boot.env;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

/**
 * 查看注入的属性源
 * 
 * @author King
 * 
 */
public class CountEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {
	
	private int order = 0;//已经是最大了
	
	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
		MutablePropertySources propertySources = environment.getPropertySources();
		for(PropertySource<?> propertySource:propertySources){
			System.err.println(propertySource.toString());
		}
	}
	
	@Override
	public int getOrder() {
		return order;
	}
}