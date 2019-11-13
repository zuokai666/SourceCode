package com.zk.discovery.shared;

import java.util.List;

import com.king.appinfo.InstanceInfo;

/**
 * 寻找服务：寻找活动的实例
 * @author King
 *
 */
public interface LookupService {
	
	//返回所有注册appName的实例的集合
	Application getApplication(String appName);
	Applications getApplications();
	List<InstanceInfo> getInstancesById(String id);
	
	//从Eureka注册信息中得到下一个可能的服务器来处理请求，采用round-robin，轮询调度算法
	InstanceInfo getNextServerFromEureka(String virtualHostname, boolean secure);
}