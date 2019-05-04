package org.zk.catalina;

import org.zk.catalina.Container;
import org.zk.catalina.Manager;

public interface Cluster {
	
    // ------------------------------------------------------------- Properties
    public String getClusterName();
    public void setClusterName(String clusterName);
    public void setContainer(Container container);
    public Container getContainer();
    
    // --------------------------------------------------------- Public Methods
    public Manager createManager(String name);
    public void registerManager(Manager manager);
    public void removeManager(Manager manager);
    
    public void backgroundProcess();
}