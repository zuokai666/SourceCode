package org.zk.catalina;

public class Globals {
	
	public static final String DEFAULT_MBEAN_DOMAIN = "Catalina";
	//安全开关是否打开
	public static final boolean IS_SECURITY_ENABLED = (System.getSecurityManager() != null);
	public static final boolean STRICT_SERVLET_COMPLIANCE = 
			Boolean.parseBoolean(System.getProperty("org.apache.catalina.STRICT_SERVLET_COMPLIANCE", "false"));
	public static final String ACTIVITY_CHECK = 
			System.getProperty("org.apache.catalina.session.StandardSession.ACTIVITY_CHECK");
	public static final String LAST_ACCESS_AT_START = 
			System.getProperty("org.apache.catalina.session.StandardSession.LAST_ACCESS_AT_START");
}