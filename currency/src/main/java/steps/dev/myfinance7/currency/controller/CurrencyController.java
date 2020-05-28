/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.currency.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import static java.time.format.DateTimeFormatter.ISO_ZONED_DATE_TIME;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import steps.dev.myfinance7.common.contract.CurrencyService;
import steps.dev.myfinance7.common.model.currency.CurrencyItem;
import steps.dev.myfinance7.common.model.quote.SecurityQuote;
import steps.dev.myfinance7.currency.repository.CurrencyRepository;

/**
 *
 * @author stepin
 */
@Controller
public class CurrencyController implements CurrencyService {

    @Autowired
    private CurrencyRepository repository;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/update_rates_by_tickets", consumes = "application/json")
    public void updateRatesByTickets(@RequestBody List<SecurityQuote> securityQuote) {
        securityQuote.stream()
                .forEach(this::translateAndUpdate);
    }

    private void translateAndUpdate(SecurityQuote quote) {
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
