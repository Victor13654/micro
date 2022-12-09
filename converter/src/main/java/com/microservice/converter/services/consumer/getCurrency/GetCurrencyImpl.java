package com.microservice.converter.services.consumer.getCurrency;

import com.microservice.converter.configuration.ServicesConfiguration;
import com.microservice.converter.services.consumer.getCurrency.model.GetCurrencyResponse;
import com.microservice.converter.services.repository.currency.CurrencyDictionaryRepository;
import com.microservice.converter.services.repository.currency.CurrencyRateRepository;
import com.microservice.converter.services.repository.currency.entities.CurrencyDictionary;
import com.microservice.converter.services.repository.currency.entities.CurrencyRate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

public class GetCurrencyImpl implements GetCurrencyFacade {

    private final ServicesConfiguration servicesConfiguration;
    private final CurrencyDictionaryRepository currencyDictionaryRepository;
    private final CurrencyRateRepository currencyRateRepository;

    public GetCurrencyImpl(ServicesConfiguration servicesConfiguration,
                           CurrencyDictionaryRepository currencyDictionaryRepository,
                           CurrencyRateRepository currencyRateRepository) {
        this.servicesConfiguration = servicesConfiguration;
        this.currencyDictionaryRepository = currencyDictionaryRepository;
        this.currencyRateRepository = currencyRateRepository;
    }

    @Override
    public void loadFromNbp() {
        GetCurrencyResponse getCurrencyResponse = getFromNbp();
        saveRates(getCurrencyResponse);
    }

    private void saveRates(GetCurrencyResponse getCurrencyResponse) {
        if (getCurrencyResponse.getRates() != null) {
            getCurrencyResponse.getRates().forEach(rate -> {
                if (rate != null && rate.getCode() != null && !rate.getCode().isEmpty()) {
                    CurrencyDictionary currencyDictionary = currencyDictionaryRepository.getReferenceById(rate.getCode());
                    CurrencyRate currencyRate = GetCurrencyMapper.mapResponseToRates(getCurrencyResponse.getEffectiveDate(), rate, currencyDictionary, LocalDateTime.now());
                    currencyRateRepository.save(currencyRate);
                }
            });
        }
    }

    private GetCurrencyResponse getFromNbp() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GetCurrencyResponse> response = restTemplate.exchange(
                servicesConfiguration.getNbpCurrencyApiUrl(),
                HttpMethod.GET,
                new HttpEntity<>(prepareHeaders()),
                GetCurrencyResponse.class
        );
        // TODO dodac exceptionHandler
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        throw new RuntimeException();
    }

    private HttpHeaders prepareHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }


}
