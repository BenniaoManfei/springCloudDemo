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
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
@RequestMapping(value="/simple/movie")
public class MovieController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private EurekaClient eurekaClient;
	
	@GetMapping(path="/user/{id}")
	@Deprecated
	public User findUser(@PathVariable Long id) {
		User user = restTemplate.getForObject("http://127.0.0.1:7900/simple/user/"+id, User.class);
		logger.info("---->请求:{}",user.toString());
		
		return user;
	}
	
	@GetMapping(path="/newuser/{id}")
	public User findUser2(@PathVariable Long id) {
		InstanceInfo instance = eurekaClient.getNextServerFromEureka("MS-PROVIDER-USER", false);
		if (instance != null) {
			User user = restTemplate.getForObject(instance.getHomePageUrl() + "/simple/user/" + id,
					User.class);
			logger.info("---->请求【地址:{},结果:{}】", instance.getHomePageUrl(), user.toString());

			return user;
		}
		
		return null;
	}
}
