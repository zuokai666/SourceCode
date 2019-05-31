package com.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.User;

/**
 * http://127.0.0.1:8080/user/6
 * @author King
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	public static final Logger log=LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping("/{id}")
    public User view(@PathVariable("id") Long id) {
		log.info("id={}",id);
        User user = new User();
        user.setId(id);
        user.setName("zuokai");
        return user;
    }
}