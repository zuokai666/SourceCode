package org.zk.catalina.util;

import javax.management.InstanceNotFoundException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import org.zk.tomcat.util.modeler.Registry;
import org.zk.catalina.Globals;
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
    
    /**
     * 必须确保子类调用super.initInternal()，否则JMX没用
     */
	@Override
	protected void initInternal() throws LifecycleException {
		if(oname == null){
			mserver = Registry.getRegistry(null, null).getMBeanServer();
            oname = register(this, getObjectNameKeyProperties());
		}
	}
	/**
	 * 拼装：domain:key=value，注册对象
	 */
	protected final ObjectName register(Object obj,String objectNameKeyProperties) {
        StringBuilder name = new StringBuilder(getDomain());
        name.append(':');
        name.append(objectNameKeyProperties);
        ObjectName on = null;
        try {
            on = new ObjectName(name.toString());
            Registry.getRegistry(null, null).registerComponent(obj, on, null);
        } catch (MalformedObjectNameException e) {
            log.warn(sm.getString("lifecycleMBeanBase.registerFail", obj, name),e);
        } catch (Exception e) {
            log.warn(sm.getString("lifecycleMBeanBase.registerFail", obj, name),e);
        }
        return on;
    }
	@Override
    public final void setDomain(String domain) {
        this.domain = domain;
    }
	@Override
    public final String getDomain() {
        if (domain == null) {
            domain = getDomainInternal();
        }
        if (domain == null) {
            domain = Globals.DEFAULT_MBEAN_DOMAIN;
        }
        return domain;
    }
	@Override
    public final ObjectName getObjectName() {
        return oname;
    }
	protected abstract String getDomainInternal();
	/**
	 * 允许子类指定key-value
	 */
	protected abstract String getObjectNameKeyProperties();
	protected final void unregister(ObjectName on) {
        if (on == null) {
            return;
        }
        if (mserver == null) {
            log.warn(sm.getString("lifecycleMBeanBase.unregisterNoServer", on));
            return;
        }
        try {
            mserver.unregisterMBean(on);
        } catch (MBeanRegistrationException e) {
            log.warn(sm.getString("lifecycleMBeanBase.unregisterFail", on), e);
        } catch (InstanceNotFoundException e) {
            log.warn(sm.getString("lifecycleMBeanBase.unregisterFail", on), e);
        }
    }
	@Override
	protected void destroyInternal() throws LifecycleException {
		unregister(oname);
	}
    @Override
    public final void postDeregister() {
        // NOOP
    }
    @Override
    public final void postRegister(Boolean registrationDone) {
        // NOOP
    }
    @Override
    public final void preDeregister() throws Exception {
        // NOOP
    }
    @Override
    public final ObjectName preRegister(MBeanServer server, ObjectName name)throws Exception {
        this.mserver = server;
        this.oname = name;
        this.domain = name.getDomain().intern();
        return oname;
    }
}