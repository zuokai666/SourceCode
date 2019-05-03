package org.zk.catalina.util;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.zk.catalina.JmxEnabled;
import org.zk.catalina.LifecycleException;
import org.zk.tomcat.util.res.StringManager;

/**
 * 
 * 
 * @author king
 * @date 2019-05-03 23:37:04
 * 
 */
public abstract class LifecycleMBeanBase extends LifecycleBase implements JmxEnabled{
	
	public static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LifecycleMBeanBase.class);
	public static final StringManager sm = StringManager.getManager(LifecycleMBeanBase.class);
	
    private String domain = null;
    private ObjectName oname = null;
    protected MBeanServer mserver = null;
    
	@Override
	protected void initInternal() throws LifecycleException {
	}
	
	@Override
	protected void startInternal() throws LifecycleException {
	}
	
	@Override
	protected void stopInternal() throws LifecycleException {
	}
	
	@Override
	protected void destroyInternal() throws LifecycleException {
	}
}