/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.exchange.gate.config;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import steps.dev.exchange.gate.client.CurrencyClient;
import steps.dev.exchange.gate.client.InstrumentClient;
import steps.dev.exchange.gate.config.moex.BondsTqobMoexReceiverConfig;
import steps.dev.exchange.gate.config.moex.BondsTqodMoexReceiverConfig;
import steps.dev.exchange.gate.config.moex.CurrenciesMoexReceiverConfig;
import steps.dev.exchange.gate.config.moex.StocksMoexReceiverConfig;
import steps.dev.exchange.gate.receiver.moex.BondsMoexReceiver;
import steps.dev.exchange.gate.receiver.moex.CurrenciesMoexReceiver;
import steps.dev.exchange.gate.receiver.moex.StocksMoexReceiver;
import steps.dev.myfinance7.common.model.currency.CurrencyRate;
import steps.dev.myfinance7.common.model.exchange.ExchangeReceiverName;
import steps.dev.myfinance7.common.model.exchange.IExchangeReceiver;

/**
 *
 * @author stepin
 */

@Configuration
public class ReceiversConfig {
    
    @Value("${generator.kafka.topic}")
    private String kafkaTopic;
    
    @Autowired
    RestTemplate restTemplate;
    
    @Autowired
    InstrumentClient instrumentClient;
    
    @Autowired
    CurrencyClient currencyClient;
    
//    @Autowired
//    KafkaTemplate<String, CurrencyRate> kafkaTemplate;    
    
    @Autowired
    private StocksMoexReceiverConfig stocksMoexReceiverConfig;
    
    @Autowired
    private BondsTqobMoexReceiverConfig bondsTqobMoexReceiverConfig;

    @Autowired
    private BondsTqodMoexReceiverConfig bondsTqodMoexReceiverConfig;
    
    @Autowired
    private CurrenciesMoexReceiverConfig currenciesMoexReceiverConfig;

    @Bean 
    public Map<ExchangeReceiverName, IExchangeReceiver> receivers(){
        
        Map<ExchangeReceiverName, IExchangeReceiver> receivers = new HashMap<>();
                
        receivers.clear();
        
        receivers.put(ExchangeReceiverName.MOSCOW_EXCHENGE_STOCKS,
                new StocksMoexReceiver(restTemplate, instrumentClient, stocksMoexReceiverConfig));
        
        receivers.put(ExchangeReceiverName.MOSCOW_EXCHENGE_BONDS_TQOB,
                new BondsMoexReceiver(restTemplate, instrumentClient, bondsTqobMoexReceiverConfig));

        receivers.put(ExchangeReceiverName.MOSCOW_EXCHENGE_BONDS_TQOD,
                new BondsMoexReceiver(restTemplate, instrumentClient, bondsTqodMoexReceiverConfig));

        receivers.put(ExchangeReceiverName.MOSCOW_EXCHENGE_CURRENCIES,
                new CurrenciesMoexReceiver(restTemplate, currencyClient, currenciesMoexReceiverConfig));
        
        return receivers;
    }
    
}
