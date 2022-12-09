package com.microservice.converter.services.provider.calculateCurrency.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class RateAndTime {

    private BigDecimal pricePLN;
    private LocalDate uploadDate;
}
