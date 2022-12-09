package com.microservice.converter.services.repository.currency;

import com.microservice.converter.services.repository.currency.entities.CurrencyDictionary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyDictionaryRepository extends JpaRepository<CurrencyDictionary, String> {
}
