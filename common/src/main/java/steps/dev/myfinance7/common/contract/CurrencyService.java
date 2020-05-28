/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.common.contract;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import steps.dev.myfinance7.common.model.quote.SecurityQuote;

/**
 *
 * @author stepin
 */
public interface CurrencyService {
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "/update_rates_by_tickets", consumes = "application/json")
    public void updateRatesByTickets(
            @RequestBody List<SecurityQuote> securityQuote) ;
                        //@RequestBody String securityQuote);
}
