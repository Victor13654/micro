package com.microservice.converter.services.provider.calculateCurrency.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class GetRateAndDateResponse {

    private String description;
    List<RateAndTime> rateAndTimeList = new ArrayList<>();

}
