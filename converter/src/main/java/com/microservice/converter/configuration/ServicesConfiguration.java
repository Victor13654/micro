package com.microservice.converter.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:services.properties")
@Getter
public class ServicesConfiguration {

    @Value("${npb.currency.api}")
    private String nbpCurrencyApiUrl;
}
