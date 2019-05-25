package org.zk.core.convert;

public class ConversionFailedException extends ConversionException {
	
	private static final long serialVersionUID = 1L;
	
	protected Class<?> sourceClass;
	protected Class<?> targetClass;
	protected Object value;
	
	public ConversionFailedException(Class<?> sourceClass,
			Class<?> targetClass,
			Object value,
			Throwable cause) {
		super("对于值"+value+"来说，从"+sourceClass+"转化到"+targetClass+"失败", cause);
		this.sourceClass = sourceClass;
		this.targetClass = targetClass;
		this.value = value;
	}
}