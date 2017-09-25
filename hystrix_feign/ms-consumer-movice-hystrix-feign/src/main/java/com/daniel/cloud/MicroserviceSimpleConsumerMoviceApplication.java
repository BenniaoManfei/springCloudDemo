package com.daniel.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker//启用熔断机制
public class MicroserviceSimpleConsumerMoviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceSimpleConsumerMoviceApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTempate() {
		return new RestTemplate();
	}
	
}
