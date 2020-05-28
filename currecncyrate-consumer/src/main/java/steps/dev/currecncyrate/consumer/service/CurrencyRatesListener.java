/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.currecncyrate.consumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import steps.dev.myfinance7.common.model.currency.CurrencyRate;

/**
 *
 * @author stepin
 */

@Service
public class CurrencyRatesListener {

    @KafkaListener (topics = "${consumer.kafka.topic}" , groupId = "${consumer.kafka.group}")
    public void listen(CurrencyRate currencyRate) {
        System.out.println("##### CurrencyRate: " + currencyRate);
        
    }
}
