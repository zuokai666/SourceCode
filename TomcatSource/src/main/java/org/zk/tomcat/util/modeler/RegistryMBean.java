package org.zk.tomcat.util.modeler;

import java.util.List;

import javax.management.ObjectName;

public interface RegistryMBean {
	
    void invoke(List<ObjectName> mbeans, String operation, boolean failFirst) throws Exception;
    void registerComponent(Object bean, String oname, String type) throws Exception;
    void unregisterComponent(String oname);
    int getId(String domain, String name);
    void stop();
}