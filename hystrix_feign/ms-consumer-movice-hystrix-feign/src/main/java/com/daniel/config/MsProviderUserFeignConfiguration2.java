package com.daniel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import feign.Contract;
import feign.Feign;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;

@Configuration
public class MsProviderUserFeignConfiguration2 {

	/*@Bean
	public Contract feignContract() {
		return new feign.Contract.Default();
	}*/
	
	
	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}
	
	@Bean
	@Scope("prototype")
	Feign.Builder feignBuilder() {
		return Feign.builder();
	}
}
