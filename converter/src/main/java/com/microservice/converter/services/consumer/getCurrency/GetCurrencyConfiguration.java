package com.microservice.converter.services.consumer.getCurrency;

import com.microservice.converter.configuration.ServicesConfiguration;
import com.microservice.converter.services.repository.currency.CurrencyDictionaryRepository;
import com.microservice.converter.services.repository.currency.CurrencyRateRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetCurrencyConfiguration {

    @Bean
    GetCurrencyFacade currencyFacade(ServicesConfiguration servicesConfiguration,
                                     CurrencyDictionaryRepository currencyDictionaryRepository,
                                     CurrencyRateRepository currencyRateRepository) {
        return new GetCurrencyImpl(servicesConfiguration, currencyDictionaryRepository, currencyRateRepository);
    }
}
