package com.example.paymentbackendtemplate.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {
        "com.example.paymentbackendtemplate.repository"
})
public class RepositoryConfig {
}
