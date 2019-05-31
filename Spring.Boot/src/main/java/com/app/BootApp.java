package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ResourceLoader;

@SpringBootApplication(scanBasePackages={"com"})
public class BootApp{
	
	public static void main(String[] args) {
		ResourceLoader resourceLoader = null;
		SpringApplication aApp = new SpringApplication(resourceLoader,BootApp.class);
		aApp.run(args);
	}
}