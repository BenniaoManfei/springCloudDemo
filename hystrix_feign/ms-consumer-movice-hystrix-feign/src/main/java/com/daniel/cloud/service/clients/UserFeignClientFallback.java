package com.daniel.cloud.service.clients;

import org.springframework.stereotype.Component;

import com.daniel.cloud.model.bean.User;

@Component
class UserFeignClientFallback implements UserFeignClient{

	@Override
	public User findById(Long id) {
		System.err.println("----->");
		User user = new User();
		user.setId(0L);
		return user;
	}
	
}

