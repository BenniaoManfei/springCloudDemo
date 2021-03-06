package com.daniel.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MicroserviceSimpleConsumerMoviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceSimpleConsumerMoviceApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTempate() {
		return new RestTemplate();
	}
	
}
