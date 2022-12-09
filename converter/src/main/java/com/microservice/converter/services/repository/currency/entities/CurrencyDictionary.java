package com.microservice.converter.services.repository.currency.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CURRENCY_DICTIONARY")
@Getter
@Setter
@NoArgsConstructor
public class CurrencyDictionary {

    @Id
    @Column(name = "ID", length = 3)
    private String id;

    @Column(name = "DESCRIPTION", length = 50, nullable = false)
    private String description;

    @OneToMany(mappedBy = "currencyDictionary", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<CurrencyRate> currencyRates = new ArrayList<>();


}