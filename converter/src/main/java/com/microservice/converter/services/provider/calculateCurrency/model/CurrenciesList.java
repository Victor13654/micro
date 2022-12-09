package com.microservice.converter.services.provider.calculateCurrency.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CurrenciesList {

    @NotNull
    @Size(max = 3)
    private String code;


}
