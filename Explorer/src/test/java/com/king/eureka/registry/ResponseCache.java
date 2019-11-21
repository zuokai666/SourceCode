package com.king.eureka.registry;

/**
 * 响应缓存
 * 
 * @author King
 *
 */
public interface ResponseCache {

	//得到关于应用的缓存信息，如果缓存信息不可用，它会在第一次请求的时候产生。在第一次之后，缓存将会被后台线程周期性的更新
	String get(Key key);
}