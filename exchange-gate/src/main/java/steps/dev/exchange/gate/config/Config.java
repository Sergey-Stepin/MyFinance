package steps.dev.exchange.gate.config;

import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import steps.dev.myfinance7.common.model.exchange.ExchangeReceiverName;
import steps.dev.myfinance7.common.model.exchange.IExchangeReceiver;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author stepin
 */

@Configuration
public class Config {
    
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    
    
}
