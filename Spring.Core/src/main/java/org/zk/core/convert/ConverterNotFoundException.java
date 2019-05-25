package org.zk.core.convert;

public class ConverterNotFoundException extends ConversionException {
	
	private static final long serialVersionUID = 1L;

	protected Class<?> sourceClass;
	protected Class<?> targetClass;
	
	public ConverterNotFoundException(Class<?> sourceClass,
			Class<?> targetClass,
			Throwable cause) {
		super("从"+sourceClass+"转化到"+targetClass+"失败，没有找到对应的转换器", cause);
		this.sourceClass = sourceClass;
		this.targetClass = targetClass;
	}
}