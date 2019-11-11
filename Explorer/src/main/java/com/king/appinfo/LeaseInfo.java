package com.king.appinfo;

/**
 * 租约信息
 * 
 * @author king
 * @date 2019-11-10 20:59:36
 *
 */
public class LeaseInfo {
	
	//客户端设置
    private int renewalIntervalInSecs = 30;//续租时间，心跳
    private int durationInSecs = 90;//租约到期时间
    //服务端设置
    private long registrationTimestamp;//注册时间戳
    private long lastRenewalTimestamp;//最后一次续租时间戳
    private long evictionTimestamp;//驱逐时间戳
    private long serviceUpTimestamp;//服务上线时间戳
    
	public int getRenewalIntervalInSecs() {
		return renewalIntervalInSecs;
	}
	public void setRenewalIntervalInSecs(int renewalIntervalInSecs) {
		this.renewalIntervalInSecs = renewalIntervalInSecs;
	}
	public int getDurationInSecs() {
		return durationInSecs;
	}
	public void setDurationInSecs(int durationInSecs) {
		this.durationInSecs = durationInSecs;
	}
	public long getRegistrationTimestamp() {
		return registrationTimestamp;
	}
	public void setRegistrationTimestamp(long registrationTimestamp) {
		this.registrationTimestamp = registrationTimestamp;
	}
	public long getLastRenewalTimestamp() {
		return lastRenewalTimestamp;
	}
	public void setLastRenewalTimestamp(long lastRenewalTimestamp) {
		this.lastRenewalTimestamp = lastRenewalTimestamp;
	}
	public long getEvictionTimestamp() {
		return evictionTimestamp;
	}
	public void setEvictionTimestamp(long evictionTimestamp) {
		this.evictionTimestamp = evictionTimestamp;
	}
	public long getServiceUpTimestamp() {
		return serviceUpTimestamp;
	}
	public void setServiceUpTimestamp(long serviceUpTimestamp) {
		this.serviceUpTimestamp = serviceUpTimestamp;
	}
}