package com.microservice.converter.services.provider.calculateCurrency;

import com.microservice.converter.services.provider.calculateCurrency.model.CalculatePriceRequest;
import com.microservice.converter.services.provider.calculateCurrency.model.CalculatePriceResponse;
import com.microservice.converter.services.provider.calculateCurrency.model.GetRateAndDateRequest;
import com.microservice.converter.services.provider.calculateCurrency.model.GetRateAndDateResponse;

public interface CalculateCurrencyFacade {
    GetRateAndDateResponse prepareRateAndData(GetRateAndDateRequest getRateAndDateRequest);
    CalculatePriceResponse calculatePrice(CalculatePriceRequest calculatePriceRequest);

}
