package com.king.discovery;

import java.util.List;

import com.zk.discovery.shared.transport.EurekaTransportConfig;

/**
 * 客户端向服务端注册实例所需要的配置
 * 
 * @author king
 * @date 2019-11-10 21:14:40
 * 
 */
public interface EurekaClientConfig {
	
	int getRegistryFetchIntervalSeconds();//多长时间从服务端取注册信息
	int getInstanceInfoReplicationIntervalSeconds();//实例信息同步
	int getInitialInstanceInfoReplicationIntervalSeconds();
	int getEurekaServiceUrlPollIntervalSeconds();
	
	int getEurekaServerReadTimeoutSeconds();
	int getEurekaServerConnectTimeoutSeconds();
	boolean shouldRegisterWithEureka();
	boolean shouldUnregisterOnShutdown();
	boolean shoudPreferSameZoneEureka();
	String getRegion();
	String[] getAvailabilityZones(String region);
	List<String> getEurekaServerServiceUrls(String myZone);
	
	boolean shouldFetchRegistry();
	
	
	EurekaTransportConfig getEurekaTransportConfig();
}