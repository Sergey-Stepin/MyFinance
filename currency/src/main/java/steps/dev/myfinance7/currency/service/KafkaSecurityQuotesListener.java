/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.currency.service;

import java.time.ZonedDateTime;
import static java.time.format.DateTimeFormatter.ISO_ZONED_DATE_TIME;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import steps.dev.myfinance7.common.model.currency.CurrencyItem;
import steps.dev.myfinance7.common.model.quote.SecurityQuote;
import steps.dev.myfinance7.currency.repository.CurrencyRepository;

/**
 *
 * @author stepin
 */

@Service
public class KafkaSecurityQuotesListener {
    
    @Autowired
    private CurrencyRepository repository;

    @KafkaListener (topics = "${kafka.consumer.topic}" , groupId = "${kafka.consumer.group}")
    public void update(SecurityQuote quote) {
        System.out.println("%%% ticket=" + quote.getTicket());
        System.out.println("%%% price=" + quote.getPrice());
        System.out.println("%%% dateTime=" + quote.getDatetime());
        ZonedDateTime dateTime = ZonedDateTime.parse(quote.getDatetime(), ISO_ZONED_DATE_TIME);
        System.out.println("%%% parsed=" + dateTime);
        Date rateDate = Date.from(dateTime.toInstant());

        CurrencyItem currency = repository.findByTicket(quote.getTicket());
        
        repository.updateRatesByTickets(
                currency.getCurrency().getCurrencyCode(), 
                repository.getBaseCurrency().getCurrencyCode(), 
                quote.getPrice(), 
                rateDate);
    }
    
    
}
