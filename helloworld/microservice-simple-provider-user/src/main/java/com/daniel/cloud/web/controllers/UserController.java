package com.daniel.cloud.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daniel.cloud.model.entity.User;
import com.daniel.cloud.repository.UserRepository;

@RestController
@RequestMapping(value="/simple/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping(value="/{id}")
	public User findById(@PathVariable Long id) {
		User user = userRepository.findOne(id);
		
		return user;
	}

}
