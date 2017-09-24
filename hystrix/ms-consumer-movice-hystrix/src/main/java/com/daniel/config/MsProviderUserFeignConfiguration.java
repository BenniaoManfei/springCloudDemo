package com.daniel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Contract;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;

@Configuration
public class MsProviderUserFeignConfiguration {

	/*@Bean
	public Contract feignContract() {
		return new feign.Contract.Default();
	}*/
	
	
	
	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}
}
