package org.zk.catalina;

import javax.management.MBeanRegistration;
import javax.management.ObjectName;

/**
 * 应用程序的监控和管理，不可避免的要使用JMX
 * JMX的一个管理构件(Managed Bean)，简称MBean
 * 资源管理
 * 
 * @author king
 * @date 2019-05-03 22:38:31
 * 
 */
public interface JmxEnabled extends MBeanRegistration {
	
    String getDomain();
    void setDomain(String domain);
    ObjectName getObjectName();
}