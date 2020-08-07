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
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import steps.dev.myfinance7.common.contract.CurrencyService;
import steps.dev.myfinance7.common.model.currency.CurrencyItem;
import steps.dev.myfinance7.common.model.exchange.ExchangeReceiverName;
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
    
//    @Override
//    @ResponseStatus(HttpStatus.OK)
//    @PostMapping(path = "/update_rates_by_tickets", consumes = "application/json")
//    public void updateRatesByTickets(@RequestBody List<SecurityQuote> securityQuote) {
//        securityQuote.stream()
//                .forEach(this::translateAndUpdate);
//    }

    @Override
    @GetMapping("/tickets_by_receiver/{exchangeReceiverName}")    
    public ResponseEntity<List<String>> getTicketsByExchangeReceiverName(String exchangeReceiverName) {
        List<String> foundTickets
                = repository
                        .findByExchangeReceiverName(ExchangeReceiverName.valueOf(exchangeReceiverName))
                        .stream()
                        .map(CurrencyItem::getTicket)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(foundTickets);
        
    }

}
