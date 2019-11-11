package com.king.appinfo;

import java.util.Map;

/**
 * 实例信息
 * 
 * @author king
 * @date 2019-11-10 19:51:54
 *
 */
public class InstanceInfo {
	
	private volatile String appName;
	//在应用名称相同的前提下，保证实例的唯一性
	private volatile String instanceId;
	
	private volatile String ipAddr;
	private volatile int port;
	
	private volatile String homePageUrl;
    private volatile String statusPageUrl;
    private volatile String healthCheckUrl;
    
    private volatile InstanceStatus status = InstanceStatus.UP;
    private volatile boolean isInstanceInfoDirty = false;
    private volatile LeaseInfo leaseInfo;
    
    private volatile Map<String, String> metadata;
    private volatile Long lastUpdatedTimestamp;
    private volatile Long lastDirtyTimestamp;
    
    
    @Override
    public int hashCode() {
    	String instanceId = getInstanceId();
    	return instanceId == null ? 31 : instanceId.hashCode() + 31;
    }
    
    @Override
    public boolean equals(Object obj) {
    	if(this == obj){
    		return true;
    	}
    	if(obj == null){
    		return false;
    	}
    	if(getClass() != obj.getClass()){
    		return false;
    	}
    	InstanceInfo other = (InstanceInfo) obj;
    	if(other.getInstanceId() != null && other.getInstanceId().equals(this.getInstanceId())){
    		return true;
    	}
    	return false;
    }
    
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getHomePageUrl() {
		return homePageUrl;
	}
	public void setHomePageUrl(String homePageUrl) {
		this.homePageUrl = homePageUrl;
	}
	public String getStatusPageUrl() {
		return statusPageUrl;
	}
	public void setStatusPageUrl(String statusPageUrl) {
		this.statusPageUrl = statusPageUrl;
	}
	public String getHealthCheckUrl() {
		return healthCheckUrl;
	}
	public void setHealthCheckUrl(String healthCheckUrl) {
		this.healthCheckUrl = healthCheckUrl;
	}
	public InstanceStatus getStatus() {
		return status;
	}
	public void setStatus(InstanceStatus status) {
		this.status = status;
	}
	public boolean isInstanceInfoDirty() {
		return isInstanceInfoDirty;
	}
	public void setInstanceInfoDirty(boolean isInstanceInfoDirty) {
		this.isInstanceInfoDirty = isInstanceInfoDirty;
	}
	public LeaseInfo getLeaseInfo() {
		return leaseInfo;
	}
	public void setLeaseInfo(LeaseInfo leaseInfo) {
		this.leaseInfo = leaseInfo;
	}
	public Map<String, String> getMetadata() {
		return metadata;
	}
	public void setMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
	}
	public Long getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}
	public void setLastUpdatedTimestamp(Long lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}
	public Long getLastDirtyTimestamp() {
		return lastDirtyTimestamp;
	}
	public void setLastDirtyTimestamp(Long lastDirtyTimestamp) {
		this.lastDirtyTimestamp = lastDirtyTimestamp;
	}
}