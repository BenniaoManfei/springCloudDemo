package com.daniel.cloud.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.daniel.cloud.model.bean.User;

@RestController
@RequestMapping(value="/simple/movie")
public class MovieController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping(path="/user/{id}")
	public User findUser(@PathVariable Long id) {
		User user = restTemplate.getForObject("http://127.0.0.1:7900/simple/user/"+id, User.class);
		logger.info("---->请求:{}",user.toString());
		
		return user;
	}
	
}
