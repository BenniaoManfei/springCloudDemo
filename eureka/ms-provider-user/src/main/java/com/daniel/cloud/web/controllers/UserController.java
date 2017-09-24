package com.daniel.cloud.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.cloud.model.entity.User;
import com.daniel.cloud.repository.UserRepository;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
@RequestMapping(value="/simple/user")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EurekaClient eurekaClient;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@GetMapping(value="/{id}")
	public User findById(@PathVariable Long id) {
	
		User user = userRepository.findOne(id);
		
		return user;
	}

	@GetMapping("/eureka-url")
	public InstanceInfo serviceUrl() {
		InstanceInfo instance = eurekaClient.getNextServerFromEureka("MS-PROVIDER-USER", false);
		
		return instance;
	}
	
	@GetMapping("/eureka-server")
	public ServiceInstance serverInfo() {
		ServiceInstance instance = discoveryClient.getLocalServiceInstance();
		
		return instance;
	}
}
