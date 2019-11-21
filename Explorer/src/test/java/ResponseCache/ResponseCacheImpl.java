package ResponseCache;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.king.eureka.EurekaServerConfig;
import com.king.eureka.registry.Key;
import com.king.eureka.registry.ResponseCache;
import com.king.eureka.registry.Value;

public class ResponseCacheImpl implements ResponseCache{
	
	//只读缓存，使用定时任务，从读写缓存往下更新只读缓存
	private final ConcurrentMap<Key, Value> readOnlyCacheMap = new ConcurrentHashMap<>();
	//缓存填充定时器
	private final Timer timer = new Timer("Eureka-CacheFillTimer", true);
	private final boolean shouldUseReadOnlyResponseCache;
	//读写缓存
	private final LoadingCache<Key, Value> readWriteCacheMap = 
			CacheBuilder.newBuilder().build(
					new CacheLoader<Key, Value>(){
						@Override
						public Value load(Key key) throws Exception {
							return null;
						}
					});
	
	//在构造方法中，开启定时器，定时更新从读写缓存往下更新只读缓存
	//并没有满足强一致性，30s的延迟
	public ResponseCacheImpl(EurekaServerConfig serverConfig){
		this.shouldUseReadOnlyResponseCache = serverConfig.shouldUseReadOnlyResponseCache();
		long responseCacheUpdateIntervalMs = serverConfig.getResponseCacheUpdateIntervalMs();
		if(shouldUseReadOnlyResponseCache){
			timer.schedule(getCacheUpdateTask(), responseCacheUpdateIntervalMs);//默认30s
		}
	}
	
	//使用定时任务，从readWriteCacheMap中复制到readOnlyCacheMap
	public TimerTask getCacheUpdateTask(){
		return new TimerTask() {
			
			@Override
			public void run() {
				for(Key key : readOnlyCacheMap.keySet()){//遍历所有的键
					try {
						Value cacheValue = readWriteCacheMap.get(key);
						Value currentCacheValue = readOnlyCacheMap.get(key);
						if(cacheValue != currentCacheValue){
							readOnlyCacheMap.put(key, cacheValue);
						}
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
				}
			}
		};
	}
	
	@Override
	public String get(Key key) {
		return getValue(key, shouldUseReadOnlyResponseCache).toString();//错误的toString
	}
	
	//如果使用了只读缓存，优先从只读中读取，读取不到去读写缓存中读取；否则全部都去读写缓存中读取
	public Value getValue(final Key key, boolean useReadOnlyCache){
		Value value = null;
		try {
			if(useReadOnlyCache){
				value = readOnlyCacheMap.get(key);
				if(value != null){
					return value;
				}else {
					value = readWriteCacheMap.get(key);
					readOnlyCacheMap.put(key, value);
					return value;
				}
			}else {
				value = readWriteCacheMap.get(key);
			}
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return value;
	}
}