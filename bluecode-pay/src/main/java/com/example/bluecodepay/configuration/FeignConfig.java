package com.example.bluecodepay.configuration;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableFeignClients(basePackages = {"com.example.bluecodepay.client"})
@EnableScheduling
@EnableAspectJAutoProxy
@ComponentScan("com.example.bluecodepay")
public class FeignConfig {

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(@Value("${bluecode.basic.auth.password}") final String password,
                                                                   @Value("${bluecode.basic.auth.username}") final String username) {
        return new BasicAuthRequestInterceptor(username, password);
    }
}
