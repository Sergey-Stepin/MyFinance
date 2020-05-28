/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.exchange.gate.client;

import org.springframework.cloud.openfeign.FeignClient;
import steps.dev.myfinance7.common.contract.CurrencyService;

/**
 *
 * @author stepin
 */

@FeignClient(name = "currency/")
public interface CurrencyClient extends CurrencyService{
    
}
