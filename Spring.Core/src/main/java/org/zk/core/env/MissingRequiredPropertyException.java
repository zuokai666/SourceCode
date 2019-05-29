package org.zk.core.env;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 缺失必要属性异常，里面存储着缺失的属性
 * 
 * @author King
 *
 */
public class MissingRequiredPropertyException extends IllegalStateException {
	
	private static final long serialVersionUID = 1L;
	
	private final Set<String> missingRequiredProperty = new LinkedHashSet<>();
	
	@Override
	public String getMessage() {
		return "以下属性是必需的，但是却找不到:" + getMissingRequiredProperty();
	}
	
	//根据需求，可能存在重复，所以这里应该判重
	public void addmissingRequiredProperty(String key){
		missingRequiredProperty.add(key);
	}
	
	public Set<String> getMissingRequiredProperty(){
		return missingRequiredProperty;
	}
}