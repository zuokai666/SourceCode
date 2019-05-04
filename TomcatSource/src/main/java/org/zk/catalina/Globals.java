package org.zk.catalina;

public class Globals {
	
	public static final String DEFAULT_MBEAN_DOMAIN = "Catalina";
	//安全开关是否打开
	public static final boolean IS_SECURITY_ENABLED = (System.getSecurityManager() != null);
}