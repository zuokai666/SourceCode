package org.zk.core.env;

import org.zk.core.convert.support.DefaultConversionService;
import org.springframework.util.Assert;
import org.zk.core.convert.support.ConfigurableConversionService;

public abstract class AbstractPropertyResolver implements ConfigurablePropertyResolver{
	
	private volatile ConfigurableConversionService conversionService;
	
	/**
	 * 基于volatile关键字的双重检测锁实现线程安全
	 */
	@Override
	public ConfigurableConversionService getConversionService() {
		ConfigurableConversionService ccs = this.conversionService;
		if(ccs == null){
			synchronized (this) {
				ccs = this.conversionService;
				if(ccs == null){
					ccs = new DefaultConversionService();
					this.conversionService = ccs;
				}
			}
		}
		return ccs;
	}
	
	public void setConversionService(ConfigurableConversionService conversionService) {
		Assert.notNull(conversionService, "conversionService不能为空");
		this.conversionService = conversionService;
	}
}