package com.example.bluecodepay.configuration;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"com.example.bluecodepay.client"})
public class FeignConfig {

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(@Value("${bluecode.basic.auth.password}") final String password,
                                                                   @Value("${bluecode.basic.auth.username}") final String username) {
        return new BasicAuthRequestInterceptor(username, password);
    }
}
