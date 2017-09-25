package com.daniel.cloud.service.clients;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.daniel.cloud.model.bean.User;
import com.daniel.config.MsProviderUserFeignConfiguration;

@FeignClient(value="ms-provider-user-hystrix-feign",path="/simple/user",configuration=MsProviderUserFeignConfiguration.class,fallback=UserFeignClientFallback.class)
public interface UserFeignClient {

	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public User findById(@PathVariable("id") Long id);
	
}
