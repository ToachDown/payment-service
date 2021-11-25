package com.example.bluecodepay.configuration;

import com.example.bluecodepay.model.request.BluecodeRefundMessage;
import com.example.bluecodepay.model.request.BluecodeRequestMessage;
import com.example.bluecodepay.model.response.BluecodeResponseMessage;
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
                new NamedType(BluecodeRequestMessage.class, "bluecode"),
                new NamedType(BluecodeResponseMessage.class, "bluecode"),
                new NamedType(BluecodeRefundMessage.class, "bluecode")
        );
    }


}
