package com.king.eureka;

public interface EurekaServerConfig {
	
	//响应缓存更新间隔时间
	long getResponseCacheUpdateIntervalMs();
	long getResponseCacheAutoExpirationInSeconds();
	
	//响应缓存目前使用二级缓存策略。一个带过期策略的读写缓存，一个不带过期的只读缓存
	//如果是true，我们就会使用只读缓存
	boolean shouldUseReadOnlyResponseCache();
}