package com.microservice.converter.services.repository.currency;

import com.microservice.converter.services.repository.currency.entities.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

import java.util.List;

public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long> {

    @Query("SELECT cur FROM CurrencyRate cur " +
            "JOIN cur.currencyDictionary dic " +
            "WHERE dic.id = :code ")
    List<CurrencyRate> getCurrencyRatesByCode(String code);

    @Query("SELECT cur FROM CurrencyRate cur " +
            "JOIN cur.currencyDictionary dic " +
            "WHERE dic.id in :codes " +
            "AND cur.uploadDate = :uploadDate")
    List<CurrencyRate> getCurrencyRateByCodeAndDate(List<String> codes, LocalDate uploadDate);

    @Query("SELECT cur FROM CurrencyRate cur " +
            "WHERE cur.uploadDate = :today ")
    List<CurrencyRate> getApplicationsCurrentDate(LocalDate today);

}
