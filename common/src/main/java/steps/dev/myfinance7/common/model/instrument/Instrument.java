/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.common.model.instrument;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Currency;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import steps.dev.myfinance7.common.model.exchange.ExchangeReceiverName;

/**
 *
 * @author stepin
 */
@Data
@Entity
public class Instrument implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long instrumentId;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InstrumentType instrumentType = InstrumentType.BANK_ACCOUNT;

    @NotNull
    @NotBlank
    @Column(nullable = false, unique = true)
    private String instrumentName;
    
    private String ticket;

    @NotNull
    @Column(nullable = false, length = 3)
    //@ManyToOne(optional = false)
    //@JoinColumn(name = "currency")
    //private CurrencyItem currencyItem;
    private Currency currency;

    @NotNull
    @Column(nullable = false)
    private double price;
    
    @NotNull
    //@Column(nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    @Column(nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP")
    //private Timestamp priceTimestamp = Timestamp.from(Instant.now());
    private ZonedDateTime priceTimestamp = ZonedDateTime.now();

    @Column(nullable = true)
    private Date maturityDate;
    
//    @ManyToOne(optional = true, fetch = FetchType.LAZY)
//    @JoinColumn(name = "exchange_receiver_type")
//    private ExchangeReceiverDescriptor exchangeReceiverDescriptor;

    @Enumerated(EnumType.STRING)
    private ExchangeReceiverName exchangeReceiverName;
    
}
