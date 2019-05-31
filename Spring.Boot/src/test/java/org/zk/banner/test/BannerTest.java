package org.zk.banner.test;

import org.springframework.boot.Banner;
import org.springframework.boot.ImageBanner;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

public class BannerTest {
	
	public static void main(String[] args) {
		Environment environment = new StandardEnvironment();
		Resource resource = new DefaultResourceLoader().getResource("sky.png");
		Banner banner = new ImageBanner(resource);
		banner.printBanner(environment, BannerTest.class, System.out);
	}
}