package com.daniel.config;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

import com.daniel.cloud.ExcludeFromComponentScan;

@Configuration
@RibbonClient(name="ms-provider-user-hystrix",configuration=MsProviderUserRibbonConfiguration.class)
public class BalanceCOnfiguration {

}
