package com.zk.boot.env;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class ListCommandLineRunner implements CommandLineRunner{
	
	@Override
	public void run(String... args) throws Exception {
		for(String arg : args){
			System.err.println("输入参数：" + arg);
		}
	}
}