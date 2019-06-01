package com.zk.boot.auto.configure;

import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

@ConditionalOnClass(Tomcat.class)
public class TestAutoConfiguration {

}