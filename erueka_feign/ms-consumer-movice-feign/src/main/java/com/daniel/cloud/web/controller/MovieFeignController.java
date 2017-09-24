package com.daniel.cloud.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.cloud.model.bean.User;

import feign.Contract;

@RestController
@RequestMapping(value="/feign/movie")
public class MovieFeignController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());;

	@Autowired
	private UserFeignClient userFeignClient;
	
	
	@GetMapping(path="/user/{id}")
	public User findUser(@PathVariable Long id) {
		User user = userFeignClient.findById(id);
		logger.info("---->请求:{}",user.toString());
		
		return user;
	}
}
