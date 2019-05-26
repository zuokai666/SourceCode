package org.zk.core.convert.converter;

/**
 * 映射关系的持有者
 * 
 * @author king
 * @date 2019-05-26 08:41:55
 *
 */
public final class ConvertiblePair {
	
	private final Class<?> sourceType;
	
	private final Class<?> targetType;
	
	public ConvertiblePair(Class<?> sourceType, Class<?> targetType) {
		this.sourceType = sourceType;
		this.targetType = targetType;
	}

	public Class<?> getSourceType() {
		return this.sourceType;
	}

	public Class<?> getTargetType() {
		return this.targetType;
	}
	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}
		if(obj == null || obj.getClass() != ConvertiblePair.class){
			return false;
		}
		ConvertiblePair otherPair = (ConvertiblePair) obj;
		return (this.sourceType == otherPair.sourceType) 
				&& (this.targetType == otherPair.targetType);
	}
	@Override
	public int hashCode() {
		return this.sourceType.hashCode()*31 + this.targetType.hashCode();
	}
	@Override
	public String toString() {
		return (this.sourceType.getName() + " -> " + this.targetType.getName());
	}
}