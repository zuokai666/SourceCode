package org.zk.core.convert.converter;

import java.util.Set;

import org.springframework.core.convert.TypeDescriptor;

public interface GenericConverter {
	
	Set<ConvertiblePair> getConvertibleTypes();
	Object convert(Object source,TypeDescriptor sourceType,TypeDescriptor targetType);
}