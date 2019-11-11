package com.king.appinfo;

import java.util.Map;

/**
 * 把一个实例注册到注册中心的所需要的配置信息
 * 对实例信息类的抽象，面向接口编程
 * 
 * @author king
 * @date 2019-11-10 21:10:47
 *
 */
public interface EurekaInstanceConfig {
	
    String getInstanceId();
    String getAppname();
    int getLeaseRenewalIntervalInSeconds();
    int getLeaseExpirationDurationInSeconds();
    String getHostName(boolean refresh);
    Map<String, String> getMetadataMap();
    String getIpAddress();
    String getStatusPageUrl();
    String getHomePageUrl();
    String getHealthCheckUrl();
}