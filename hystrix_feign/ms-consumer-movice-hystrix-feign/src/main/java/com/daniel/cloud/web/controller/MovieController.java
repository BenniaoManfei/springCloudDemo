package com.daniel.cloud.web.controller;

import javax.jws.soap.SOAPBinding.Use;

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
import com.daniel.cloud.service.clients.UserFeignClient;
import com.daniel.cloud.service.clients.UserFeignClient2;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping(value="/movie")
public class MovieController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private EurekaClient eurekaClient;
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	@Autowired
	private UserFeignClient2 userFeignClient2;
	
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
		InstanceInfo instance = eurekaClient.getNextServerFromEureka("MS-PROVIDER-USER-HYSTRIX2", false);
		if (instance != null) {
			User user = restTemplate.getForObject(instance.getHomePageUrl() + "/simple/user/" + id,
					User.class);
			logger.info("---->请求【地址:{},结果:{}】", instance.getHomePageUrl(), user.toString());

			return user;
		}
		
		return null;
	}*/
	
	@GetMapping(path="/newuser/{id}")
	@HystrixCommand(fallbackMethod="findUser2Fallback")
	public User findUser2(@PathVariable Long id) {
	
		User user = restTemplate.getForObject("http://ms-provider-user-hystrix-feign/simple/user/"+id, User.class);
		logger.info("---->请求【结果:{}】",user.toString());
		
		return user;
	}
	
	
	public User findUser2Fallback(Long id) {
		User user = new User();
		user.setId(0L);
		return user;
	}
	@GetMapping(path="/newuser/feign/{id}")
	public User findUser2ByFeign(@PathVariable Long id) {
		return userFeignClient.findById(id);
	
	}
	
	@GetMapping(path="/newuser/feign2/{id}")
	public User findUser2ByFeign2(@PathVariable Long id) {
		return userFeignClient2.findById(id);
	
	}
	
	@GetMapping(path="/newuser3/{id}")
	public ServiceInstance findUser3(@PathVariable Long id) {
		ServiceInstance instance = loadBalancerClient.choose("ms-provider-user-hystrix-feign");
		logger.info("服务HOST信息:【host:{},port:{},instance:{}】",instance.getHost(),instance.getPort(),instance.getServiceId());
		return	instance;		
	}
}
