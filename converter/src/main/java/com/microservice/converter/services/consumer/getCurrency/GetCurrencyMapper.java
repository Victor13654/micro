package com.microservice.converter.services.consumer.getCurrency;

import com.microservice.converter.services.consumer.getCurrency.model.Rate;
import com.microservice.converter.services.repository.currency.entities.CurrencyDictionary;
import com.microservice.converter.services.repository.currency.entities.CurrencyRate;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;

@UtilityClass
public class GetCurrencyMapper {

    public CurrencyRate mapResponseToRates(LocalDate effectiveDate, Rate rate, CurrencyDictionary currencyDictionary, LocalDateTime now) {
        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.setCurrencyDictionary(currencyDictionary);
        currencyRate.setCurrencyBid(rate.getBid());
        currencyRate.setCurrencyAsk(rate.getAsk());
        currencyRate.setUploadDate(effectiveDate);
        currencyRate.setModifiedOn(now);

        return currencyRate;
    }

}
