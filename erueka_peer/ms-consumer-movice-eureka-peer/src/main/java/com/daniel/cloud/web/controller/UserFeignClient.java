package com.daniel.cloud.web.controller;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.daniel.cloud.model.bean.User;
import com.daniel.config.MsProviderUserFeignConfiguration;

import feign.Param;
import feign.RequestLine;


@FeignClient(value="ms-provider-user-eureka-peer",configuration=MsProviderUserFeignConfiguration.class,path="/simple/user")
public interface UserFeignClient {

	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public User findById(@PathVariable("id") Long id);

	/*	@feign.RequestLine("GET /{id}")
	public User findById(@feign.Param("id") Long id);*/
}
