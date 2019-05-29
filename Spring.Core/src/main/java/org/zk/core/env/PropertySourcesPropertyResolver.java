package org.zk.core.env;

public class PropertySourcesPropertyResolver extends AbstractPropertyResolver{
	
	@SuppressWarnings("unused")
	private final PropertySources propertySources;
	
	public PropertySourcesPropertyResolver(PropertySources propertySources) {
		this.propertySources = propertySources;
	}
	
	@Override
	public void setPlaceHolderPrefix(String placeHolderPrefix) {
	}

	@Override
	public void setPlaceHoldersuffix(String placeHoldersuffix) {
	}

	@Override
	public void setValueSeparator(String valueSeparator) {
	}

	@Override
	public void setIgnoreUnresolvableNestedPlaceholders(boolean ignoreUnresolvableNestedPlaceholders) {
	}

	@Override
	public void setRequiredProperties(String... requiredProperties) {
	}

	@Override
	public void validateRequiredProperties() throws MissingRequiredPropertyException {
	}
}