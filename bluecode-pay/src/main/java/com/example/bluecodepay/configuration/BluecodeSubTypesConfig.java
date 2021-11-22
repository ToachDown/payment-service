package com.example.bluecodepay.configuration;

import com.example.bluecodepay.model.request.RefundMessageBluecode;
import com.example.bluecodepay.model.request.RequestMessageBluecode;
import com.example.bluecodepay.model.response.ResponseMessageBluecode;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ComponentScan(basePackages = "com.example.bluecodepay")
public class BluecodeSubTypesConfig {

    @Bean
    public List<NamedType> listSubTypes() {
        return List.of(
                new NamedType(RequestMessageBluecode.class, "bluecode"),
                new NamedType(ResponseMessageBluecode.class, "bluecode"),
                new NamedType(RefundMessageBluecode.class, "bluecode")
        );
    }


}
