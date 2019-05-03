package org.zk.catalina;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.regex.Pattern;

interface Host extends Container{
	
	String ADD_ALIAS_EVENT = "addAlias";
    String REMOVE_ALIAS_EVENT = "removeAlias";
	
    // ------------------------------------------------------------- Properties
    String getXmlBase();
    void setXmlBase(String xmlBase);
    File getConfigBaseFile();
    String getAppBase();
    File getAppBaseFile();
    void setAppBase(String appBase);
    boolean getAutoDeploy();
    void setAutoDeploy(boolean autoDeploy);
    String getConfigClass();
    void setConfigClass(String configClass);
    boolean getDeployOnStartup();
    void setDeployOnStartup(boolean deployOnStartup);
    String getDeployIgnore();
    Pattern getDeployIgnorePattern();
    void setDeployIgnore(String deployIgnore);
    ExecutorService getStartStopExecutor();
    boolean getCreateDirs();
    void setCreateDirs(boolean createDirs);
    boolean getUndeployOldVersions();
    void setUndeployOldVersions(boolean undeployOldVersions);
    
    void addAlias(String alias);
    String[] findAliases();
    void removeAlias(String alias);
}