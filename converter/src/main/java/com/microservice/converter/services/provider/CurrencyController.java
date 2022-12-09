package com.microservice.converter.services.provider;

import com.microservice.converter.services.provider.calculateCurrency.CalculateCurrencyFacade;
import com.microservice.converter.services.provider.calculateCurrency.model.CalculatePriceRequest;
import com.microservice.converter.services.provider.calculateCurrency.model.CalculatePriceResponse;
import com.microservice.converter.services.provider.calculateCurrency.model.GetRateAndDateRequest;
import com.microservice.converter.services.provider.calculateCurrency.model.GetRateAndDateResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/currency")
public class CurrencyController {

    private final CalculateCurrencyFacade calculateCurrencyFacade;

    public CurrencyController(CalculateCurrencyFacade calculateCurrencyFacade) {
        this.calculateCurrencyFacade = calculateCurrencyFacade;
    }

    @PostMapping("/getRateAndDate")
    @ApiOperation(value = "Returns Rates and Date")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request przetworzony poprawnie", response = GetRateAndDateResponse.class),
    })
    public ResponseEntity<GetRateAndDateResponse> getRateAndDate(@Valid @RequestBody final GetRateAndDateRequest request) {

        GetRateAndDateResponse response = calculateCurrencyFacade.prepareRateAndData(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/calculatePrice")
    @ApiOperation(value = "Returns summ of rates")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request przetworzony poprawnie", response = CalculatePriceResponse.class),
    })
    public ResponseEntity<CalculatePriceResponse> filter(@Valid @RequestBody final CalculatePriceRequest request) {

        CalculatePriceResponse response = calculateCurrencyFacade.calculatePrice(request);

        return ResponseEntity.ok(response);
    }

}
