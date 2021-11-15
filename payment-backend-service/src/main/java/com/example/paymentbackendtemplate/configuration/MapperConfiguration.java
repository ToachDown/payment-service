package com.example.paymentbackendtemplate.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return objectMapper;
    }
}
