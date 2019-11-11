package com.king.appinfo;

/**
 * 实例状态
 * 
 * @author king
 * @date 2019-11-10 20:30:50
 *
 */
public enum InstanceStatus {
	UP, // Ready to receive traffic
	DOWN, // Do not send traffic- healthcheck callback failed
	STARTING, // Just about starting- initializations to be done do not send traffic
	OUT_OF_SERVICE, // Intentionally shutdown for traffic
	UNKNOWN;
}