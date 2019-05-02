package org.zk.catalina;

public interface Host extends Container{
	
	
	
	
    void addAlias(String alias);
    String[] findAliases();
    void removeAlias(String alias);
}