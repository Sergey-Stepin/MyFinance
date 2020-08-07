/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.common.contract;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import steps.dev.myfinance7.common.model.instrument.Instrument;
import steps.dev.myfinance7.common.model.quote.SecurityQuote;

/**
 *
 * @author stepin
   */

@RequestMapping(path="/instrument", produces = "application/json")
public interface InstrumentService{

    @GetMapping("test")
    public ResponseEntity<String> getTest();
    
    @GetMapping
    public ResponseEntity<List<Instrument>> getAll();
    
    @GetMapping("/{id}")
    public ResponseEntity<Instrument> getById(@PathVariable("id") long id) ;

    @GetMapping("/tickets_by_receiver/{exchangeReceiverName}")
    public ResponseEntity<List<String>> getTicketsByExchangeReceiverName(@PathVariable("exchangeReceiverName") String exchangeReceiverName) ;
    
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Instrument> post(@RequestBody Instrument instrument) ;

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void put(@RequestBody Instrument instrument) ;

    @PatchMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<Instrument> patch(
            @PathVariable("id") long id,
            @RequestBody Instrument patch) ;

//    @ResponseStatus(HttpStatus.OK)
//    @PostMapping(path = "/update_prices_by_tickets", consumes = "application/json")
//    public void updateQuotesByTickets(
//            @RequestBody List<SecurityQuote> securityQuote) ;
//                        //@RequestBody String securityQuote);
            
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) ;
    
}
