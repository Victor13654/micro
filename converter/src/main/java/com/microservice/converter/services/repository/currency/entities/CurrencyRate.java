package com.microservice.converter.services.repository.currency.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "CURRENCY_RATE")
@Getter
@Setter
@NoArgsConstructor
public class CurrencyRate {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "currencyRateGen")
    @SequenceGenerator(name = "currencyRateGen", sequenceName = "CURRENCY_RATE_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CURRENCY_ID", nullable = false)
    private CurrencyDictionary currencyDictionary;

    @Column(name = "CURRENCY_BID", length = 14, precision = 2, nullable = false)
    private BigDecimal currencyBid;

    @Column(name = "CURRENCY_ASK", length = 14, precision = 2, nullable = false)
    private BigDecimal currencyAsk;

    @Column(name = "UPLOAD_DATE")
    private LocalDate uploadDate;

    @Version
    @Column(name = "VERSION", nullable = false)
    private long version = 0L;

    @Column(name = "MODIFIED_ON")
    private LocalDateTime modifiedOn;

}