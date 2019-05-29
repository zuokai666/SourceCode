package org.zk.core.env;

import java.util.Map;

public interface ConfigurableEnvironment extends Environment, ConfigurablePropertyResolver{
	
	void setActiveProfiles(String... profiles);
	void addActiveProfile(String profile);
	void setDefaultProfiles(String... profiles);
	
	Map<String, Object> getSystemProperties();//System.getProperties();
	Map<String, Object> getSystemEnvironment();//System.getenv();
	
	MutablePropertySources getPropertySources();
	void merge(ConfigurableEnvironment parentConfigurableEnvironment);
}