package org.zk.classify;

public interface Classifier<K,V> {
	
	V classify(K classifiable);
}