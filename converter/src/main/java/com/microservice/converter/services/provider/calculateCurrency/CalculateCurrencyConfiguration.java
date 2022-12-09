package com.microservice.converter.services.provider.calculateCurrency;

import com.microservice.converter.services.consumer.getCurrency.GetCurrencyFacade;
import com.microservice.converter.services.repository.currency.CurrencyRateRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalculateCurrencyConfiguration {

    @Bean
    CalculateCurrencyFacade calculateCurrencyFacade(CurrencyRateRepository currencyRateRepository,
                                                    GetCurrencyFacade getCurrencyFacade) {
        return new CalculateCurrencyImpl(currencyRateRepository, getCurrencyFacade);
    }
}
