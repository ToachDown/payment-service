package com.example.paymentbackendtemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import xyz.lxie.feign.springboot.EnableFeignClients;

@SpringBootApplication()
@EnableJpaRepositories
@EnableFeignClients
public class PaymentBackendTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentBackendTemplateApplication.class, args);
    }

}
