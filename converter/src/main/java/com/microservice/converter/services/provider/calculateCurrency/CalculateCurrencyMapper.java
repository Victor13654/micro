package com.microservice.converter.services.provider.calculateCurrency;

import com.microservice.converter.services.provider.calculateCurrency.model.GetRateAndDateResponse;
import com.microservice.converter.services.provider.calculateCurrency.model.RateAndTime;
import com.microservice.converter.services.repository.currency.entities.CurrencyRate;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CalculateCurrencyMapper {

    RateAndTime prepareRateAndTime(CurrencyRate currencyRate) {
        return RateAndTime.builder()
                .pricePLN(currencyRate.getCurrencyAsk())
                .uploadDate(currencyRate.getUploadDate())
                .build();
    }

    GetRateAndDateResponse prepareGetRateAndDateResponse(CurrencyRate currencyRate) {
        return GetRateAndDateResponse.builder()
                .description(currencyRate.getCurrencyDictionary().getDescription())
                .build();
    }

}
