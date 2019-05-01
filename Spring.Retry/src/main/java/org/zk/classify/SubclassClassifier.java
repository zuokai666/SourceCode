package org.zk.classify;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class SubclassClassifier<K,V> implements Classifier<K,V>{
	
	private Map<Class<? extends K>, V> classified=new TreeMap<Class<? extends K>, V>(new ClassComparator());
	private V defaultValue;
	
	public SubclassClassifier(Map<Class<? extends K>, V> typeMap, V defaultValue) {
		this.classified.putAll(typeMap);
		this.defaultValue = defaultValue;
	}
	
	@Override
	public V classify(K classifiable) {
		if(classifiable == null) return defaultValue;
		@SuppressWarnings("unchecked")
		Class<? extends K> exceptionClass=(Class<? extends K>) classifiable.getClass();
		if(classified.containsKey(exceptionClass)){
			return classified.get(exceptionClass);
		}
		Iterator<Entry<Class<? extends K>, V>> iterator = classified.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<java.lang.Class<? extends K>, V> e = iterator.next();
			Class<? extends K> key = e.getKey();
            V value = e.getValue();
            if (key.isAssignableFrom(exceptionClass)) {
				this.classified.put(exceptionClass, value);
				return value;
			}
		}
		return defaultValue;
	}
}