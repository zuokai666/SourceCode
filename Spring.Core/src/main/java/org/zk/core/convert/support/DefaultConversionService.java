package org.zk.core.convert.support;

import org.zk.core.convert.converter.Converter;
import org.zk.core.convert.converter.ConverterFactory;
import org.zk.core.convert.converter.GenericConverter;

public class DefaultConversionService implements ConfigurableConversionService{
	
	@Override
	public boolean canConvert() {
		return false;
	}

	@Override
	public <T> T convert(Object source, Class<T> targetType) {
		return null;
	}

	@Override
	public void addConverter(Converter<?, ?> converter) {
	}

	@Override
	public <S, T> void addConverter(Class<S> sourceClass, Class<T> targetClass, Converter<S, T> converter) {
	}

	@Override
	public void removeConvertible(Class<?> sourceClass, Class<?> targetClass) {
	}

	@Override
	public void addGenericConverter(GenericConverter converter) {
	}

	@Override
	public void addConverterFactory(ConverterFactory<?, ?> factory) {
	}
}