package com.king.eureka.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.king.appinfo.InstanceInfo;
import com.king.eureka.lease.Lease;

/**
 * 处理所有来自客户端的注册请求
 * 
 * @author King
 *
 */
@SuppressWarnings("unused")
public class AbstractInstanceRegistry implements InstanceRegistry{
	
	private static final ConcurrentMap<String, Map<String, Lease<InstanceInfo>>> registry = new ConcurrentHashMap<>();
	
	private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private final Lock read = readWriteLock.readLock();
	private final Lock write = readWriteLock.writeLock();
	
	public void register(InstanceInfo registrant, int leaseDuration, boolean isReplication){
		try {
			read.lock();
			Map<String, Lease<InstanceInfo>> gMap = registry.get(registrant.getAppName());
			if(gMap == null){
				final ConcurrentHashMap<String, Lease<InstanceInfo>> gNewMap = new ConcurrentHashMap<>();
				gMap = registry.putIfAbsent(registrant.getAppName(), gNewMap);
				if(gMap == null){
					gMap = gNewMap;
				}
			}
			Lease<InstanceInfo> existingLease = gMap.get(registrant.getInstanceId());
			Lease<InstanceInfo> lease = new Lease<>(registrant, leaseDuration);
			gMap.put(registrant.getInstanceId(), lease);
			
		} finally {
			read.unlock();
		}
	}
	
	//使得缓存无效
	private void invalidateCache(String appName, String vipAddress, String secureVipAddress){
		//调用ResponseCache的invalidate()使得对应key无效
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}