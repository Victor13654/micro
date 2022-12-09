package com.microservice.converter.services.provider.calculateCurrency.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CalculatePriceResponse {

    private BigDecimal price;
}
