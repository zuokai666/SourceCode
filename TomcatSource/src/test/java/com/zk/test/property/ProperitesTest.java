package com.zk.test.property;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 测试ResourceBundle 和 MessageFormat这两个类来读取属性文件
 * 
 * @author king
 * @date 2019-05-03 14:55:18
 *
 */
public class ProperitesTest {
	
	public static final Logger log = LoggerFactory.getLogger(ProperitesTest.class);
	
	public static void main(String[] args) {
		//指明包路径和文件名即可
        ResourceBundle resource = ResourceBundle.getBundle("com.zk.test.property.test");
        String driverName = resource.getString("database.driver");
        String url = resource.getString("database.url");
        //取得字符串，直接格式化
        String user = MessageFormat.format(resource.getString("database.user"), new Object[]{"root"});
        String pass = MessageFormat.format(resource.getString("database.pass"), new Object[]{"test"});
        log.info("driverName:{}",driverName);
        log.info("url:{}",url);
        log.info("user:{}",user);
        log.info("pass:{}",pass);
	}
}