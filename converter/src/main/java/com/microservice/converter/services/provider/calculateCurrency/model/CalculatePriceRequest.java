package com.microservice.converter.services.provider.calculateCurrency.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class CalculatePriceRequest {

    @NotNull
    private LocalDate uploadDate;

    @NotNull
    private List<CurrenciesList> currenciesListAndAmounts;
}
