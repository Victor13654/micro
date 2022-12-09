package com.microservice.converter.services.provider.calculateCurrency.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRateAndDateRequest {

    @Valid
    @NotNull
    @Size(max = 200)
    @JsonProperty("code")
    private String code;

}
