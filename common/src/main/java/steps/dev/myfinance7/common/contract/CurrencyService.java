/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.common.contract;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author stepin
 */
public interface CurrencyService {
    
    @GetMapping("/tickets_by_receiver/{exchangeReceiverName}")
    public ResponseEntity<List<String>> getTicketsByExchangeReceiverName(@PathVariable("exchangeReceiverName") String exchangeReceiverName) ;
    
    
}
