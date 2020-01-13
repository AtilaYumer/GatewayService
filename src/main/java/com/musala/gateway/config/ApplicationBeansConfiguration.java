package com.musala.gateway.config;

import com.musala.gateway.service.GatewayService;
import com.musala.gateway.service.GatewayServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeansConfiguration {

    @Bean
    public GatewayService gatewayService() {
        return new GatewayServiceImpl();
    }
}
