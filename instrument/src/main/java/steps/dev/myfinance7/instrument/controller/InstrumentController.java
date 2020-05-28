/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.instrument.controller;

import java.time.ZonedDateTime;
import static java.time.format.DateTimeFormatter.ISO_ZONED_DATE_TIME;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import steps.dev.myfinance7.common.contract.InstrumentService;
import steps.dev.myfinance7.common.model.exchange.ExchangeReceiverName;
import steps.dev.myfinance7.common.model.instrument.Instrument;
import steps.dev.myfinance7.common.model.quote.SecurityQuote;
import steps.dev.myfinance7.instrument.repository.InstrumentRepository;

/**
 *
 * @author stepin
 */
@Controller
public class InstrumentController implements InstrumentService {

    @Autowired
    private InstrumentRepository repository;

    @GetMapping("test")
    @Override
    public ResponseEntity<String> getTest() {
        return new ResponseEntity<>(this.getClass().getName(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Instrument>> getAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Instrument> getById(@PathVariable("id") long id) {
        return new ResponseEntity<>(
                repository.findById(id).get(),
                HttpStatus.OK);
    }

    @GetMapping("/tickets_by_receiver/{exchangeReceiverName}")
    @Override
    public ResponseEntity<List<String>> getTicketsByExchangeReceiverName(@PathVariable("exchangeReceiverName") String exchangeReceiverName) {

        List<String> foundTickets
                = repository
                        .findByExchangeReceiverName(ExchangeReceiverName.valueOf(exchangeReceiverName))
                        .stream()
                        .map(Instrument::getTicket)
                        .collect(Collectors.toList());

        return ResponseEntity.ok(foundTickets);
    }

    @PostMapping(consumes = "application/json")
    @Override
    public ResponseEntity<Instrument> post(@RequestBody Instrument instrument) {
        System.out.println("### SAVE:" + instrument);
        return new ResponseEntity<>(
                repository.save(instrument),
                HttpStatus.CREATED);
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public void put(@RequestBody Instrument instrument) {
        repository.save(instrument);
        //return repository.save(instrument);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/update_prices_by_tickets", consumes = "application/json")
    public void updateQuotesByTickets(
            //@RequestBody String securityQuote){
            //        System.out.println("$$$$$$ securityQuote=" + securityQuote);
            @RequestBody List<SecurityQuote> securityQuote) {

        securityQuote.stream()
                .forEach(this::translateAndUpdate);
        ;
    }

    private void translateAndUpdate(SecurityQuote quote) {
        System.out.println("%%% ticket=" + quote.getTicket());
        System.out.println("%%% price=" + quote.getPrice());
        System.out.println("%%% dateTime=" + quote.getDatetime());
        ZonedDateTime datetime = ZonedDateTime.parse(quote.getDatetime(), ISO_ZONED_DATE_TIME);
        System.out.println("%%% parsed=" + datetime);
        
        repository.updatePricesByTickets(
                quote.getPrice(), 
                datetime,
                quote.getTicket());        
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    @Override
    public ResponseEntity<Instrument> patch(
            @PathVariable("id") long id,
            @RequestBody Instrument patch) {

        Instrument instrument;
        try {
            //instrument = repository.findById(idId).orElseThrow(() -> new EmptyResultDataAccessException("no instrument is foind for id:" + idId, 1));
            instrument = repository.findById(id)
                    .get();
        } catch (EmptyResultDataAccessException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        if (patch.getInstrumentName() != null) {
            instrument.setInstrumentName(patch.getInstrumentName());
        }

        if (patch.getCurrency()!= null) {
            instrument.setCurrency(patch.getCurrency());
        }

        return new ResponseEntity<>(repository.save(instrument), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void delete(@PathVariable("id") long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
        }
    }

}
