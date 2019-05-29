package org.zk.core.env;

public interface Environment extends PropertyResolver{
	
	String[] getActiveProfiles();
	String[] getDefaultProfiles();
	boolean acceptsProfiles(Profiles profiles);
}