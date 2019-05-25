package org.zk.core.convert;

/**
 * 转换服务的抽象基类
 * 
 * @author King
 * 
 */
public abstract class ConversionException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public ConversionException(String message) {
        super(message);
    }
	
	public ConversionException(String message, Throwable cause) {
        super(message, cause);
    }
}