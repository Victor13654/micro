package com.microservice.converter.services.provider.calculateCurrency;

import com.microservice.converter.services.consumer.getCurrency.GetCurrencyFacade;
import com.microservice.converter.services.provider.calculateCurrency.model.CalculatePriceRequest;
import com.microservice.converter.services.provider.calculateCurrency.model.CalculatePriceResponse;
import com.microservice.converter.services.provider.calculateCurrency.model.CurrenciesList;
import com.microservice.converter.services.provider.calculateCurrency.model.GetRateAndDateRequest;
import com.microservice.converter.services.provider.calculateCurrency.model.GetRateAndDateResponse;
import com.microservice.converter.services.repository.currency.CurrencyRateRepository;
import com.microservice.converter.services.repository.currency.entities.CurrencyRate;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CalculateCurrencyImpl implements CalculateCurrencyFacade {

    private final CurrencyRateRepository currencyRateRepository;
    private final GetCurrencyFacade getCurrencyFacade;

    public CalculateCurrencyImpl(CurrencyRateRepository currencyRateRepository, GetCurrencyFacade getCurrencyFacade) {
        this.currencyRateRepository = currencyRateRepository;
        this.getCurrencyFacade = getCurrencyFacade;
    }

    @Transactional
    @Override
    public GetRateAndDateResponse prepareRateAndData(GetRateAndDateRequest getRateAndDateRequest) {
        ratesAreActual();
        List<CurrencyRate> currencyRates = currencyRateRepository.getCurrencyRatesByCode(getRateAndDateRequest.getCode());
        if (!currencyRates.isEmpty()) {
            GetRateAndDateResponse getRateAndDateResponse = CalculateCurrencyMapper.prepareGetRateAndDateResponse(currencyRates.get(0));
            getRateAndDateResponse
                    .getRateAndTimeList()
                    .addAll(currencyRates
                            .stream()
                            .map(CalculateCurrencyMapper::prepareRateAndTime)
                            .collect(Collectors.toList()));
            return getRateAndDateResponse;
        }
        throw new RuntimeException();
    }

    @Transactional
    @Override
    public CalculatePriceResponse calculatePrice(CalculatePriceRequest calculatePriceRequest) {
        ratesAreActual();
        List<CurrencyRate> currencyRates =
                currencyRateRepository.getCurrencyRateByCodeAndDate(
                        calculatePriceRequest.getCurrenciesListAndAmounts()
                                .stream()
                                .map(CurrenciesList::getCode)
                                .collect(Collectors.toList()),
                        calculatePriceRequest.getUploadDate());

        if (!currencyRates.isEmpty()) {
            return CalculatePriceResponse.builder()
                    .price(calculate(currencyRates))
                    .build();
        }
        throw new RuntimeException();
    }


    public void ratesAreActual() {
        List<CurrencyRate> applicationsCurrentDateList = currencyRateRepository.getApplicationsCurrentDate(LocalDate.now());
        if (applicationsCurrentDateList == null || applicationsCurrentDateList.isEmpty()) {
            getCurrencyFacade.loadFromNbp();
        }
    }

    private BigDecimal calculate(List<CurrencyRate> currencyRates) {
        BigDecimal price = new BigDecimal(0);
        for (CurrencyRate currencyRate: currencyRates) {
            price = price.add(currencyRate.getCurrencyAsk());
        }
        return price;
    }
}
