/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.exchange.gate.client;

import org.springframework.cloud.openfeign.FeignClient;
import steps.dev.myfinance7.common.contract.InstrumentService;

/**
 *
 * @author stepin
 */

@FeignClient(name = "instrument/", configuration=InstrumentClientConfig.class)
public interface InstrumentClient extends InstrumentService{
    
}
