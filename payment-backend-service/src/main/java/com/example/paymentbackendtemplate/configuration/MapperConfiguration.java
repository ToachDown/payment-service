package com.example.paymentbackendtemplate.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Configuration
public class MapperConfiguration {

    @Bean
    public ObjectMapper getConfiguredMapper (List<List<NamedType>> subTypesList) {
        final ObjectMapper objectMapper = new ObjectMapper();
        final NamedType[] subTypes = subTypesList.stream()
                .flatMap(Collection::stream)
                .toArray(NamedType[]::new);
        objectMapper.registerSubtypes(subTypes);
        return objectMapper;
    }
}
