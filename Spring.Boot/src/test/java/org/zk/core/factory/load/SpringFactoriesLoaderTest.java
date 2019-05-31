package org.zk.core.factory.load;

import java.util.List;

import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.io.support.SpringFactoriesLoader;

public class SpringFactoriesLoaderTest {
	
	public static void main(String[] args) {
		Class<?> factoryClass = PropertySourceLoader.class;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		List<String> result = SpringFactoriesLoader.loadFactoryNames(factoryClass, classLoader);
		for(String con : result){
			System.out.println(con);
		}
	}
}