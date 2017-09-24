package com.daniel.cloud.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.daniel.cloud.model.bean.User;
import com.netflix.discovery.EurekaClient;

@RestController
@RequestMapping(value="/simple/movie")
public class MovieController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private EurekaClient eurekaClient;
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	@GetMapping(path="/user/{id}")
	@Deprecated
	public User findUser(@PathVariable Long id) {
		User user = restTemplate.getForObject("http://127.0.0.1:7900/simple/user/"+id, User.class);
		logger.info("---->请求:{}",user.toString());
		
		return user;
	}
	
	/*@GetMapping(path="/newuser/{id}")
	public User findUser2(@PathVariable Long id) {
		InstanceInfo instance = eurekaClient.getNextServerFromEureka("MS-PROVIDER-USER-RIBBON", false);
		if (instance != null) {
			User user = restTemplate.getForObject(instance.getHomePageUrl() + "/simple/user/" + id,
					User.class);
			logger.info("---->请求【地址:{},结果:{}】", instance.getHomePageUrl(), user.toString());

			return user;
		}
		
		return null;
	}*/
	
	@GetMapping(path="/newuser/{id}")
	public User findUser2(@PathVariable Long id) {
	
		User user = restTemplate.getForObject("http://ms-provider-user-ribbon/simple/user/"+id, User.class);
		logger.info("---->请求【结果:{}】",user.toString());
		
		return user;
	}
	
	@GetMapping(path="/newuser3/{id}")
	public ServiceInstance findUser3(@PathVariable Long id) {
		ServiceInstance instance = loadBalancerClient.choose("ms-provider-user-ribbon");
		logger.info("服务HOST信息:【host:{},port:{},instance:{}】",instance.getHost(),instance.getPort(),instance.getServiceId());
		return	instance;		
	}
}
