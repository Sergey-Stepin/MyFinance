/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.exchange.gate.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
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
import steps.dev.myfinance7.common.model.quote.SecurityQuote;

/**
 *
 * @author stepin
 */

@Configuration
public class ReceiversConfig {
    
    @Autowired
    RestTemplate restTemplate;
    
    @Autowired
    InstrumentClient instrumentClient;
    
    @Autowired
    CurrencyClient currencyClient;

    @Value("${kafka.currency-rates.producer.topic}")
    private String kafkaCurrencyRatesTopic;
    
    @Value("${kafka.security-quotes.producer.topic}")
    private String kafkaSecurityQuotesTopic;
    
    
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
    @Qualifier("receivers")
    public Map<ExchangeReceiverName, IExchangeReceiver> receivers(){
        
        Map<ExchangeReceiverName, IExchangeReceiver> receivers = new HashMap<>();
                
        receivers.clear();
        
        receivers.put(ExchangeReceiverName.MOSCOW_EXCHENGE_STOCKS,
                new StocksMoexReceiver(
                        ExchangeReceiverName.MOSCOW_EXCHENGE_STOCKS, 
                        restTemplate, 
                        instrumentClient, 
                        kafkaTemplate(), 
                        kafkaSecurityQuotesTopic,
                        stocksMoexReceiverConfig));
        
        receivers.put(
                ExchangeReceiverName.MOSCOW_EXCHENGE_BONDS_TQOB,
                new BondsMoexReceiver(
                        ExchangeReceiverName.MOSCOW_EXCHENGE_BONDS_TQOB, 
                        restTemplate, 
                        instrumentClient, 
                        kafkaTemplate(), 
                        kafkaSecurityQuotesTopic,
                        bondsTqobMoexReceiverConfig));

        receivers.put(
                ExchangeReceiverName.MOSCOW_EXCHENGE_BONDS_TQOD,
                new BondsMoexReceiver(
                        ExchangeReceiverName.MOSCOW_EXCHENGE_BONDS_TQOD,
                        restTemplate, 
                        instrumentClient, 
                        kafkaTemplate(), 
                        kafkaSecurityQuotesTopic,
                        bondsTqodMoexReceiverConfig));

        receivers.put(
                ExchangeReceiverName.MOSCOW_EXCHENGE_CURRENCIES,
                new CurrenciesMoexReceiver(
                        ExchangeReceiverName.MOSCOW_EXCHENGE_CURRENCIES,
                        restTemplate, 
                        currencyClient,                        
                        kafkaTemplate(), 
                        kafkaCurrencyRatesTopic,
                        currenciesMoexReceiverConfig));
        
        return receivers;
    }
    
    @Bean
    public ProducerFactory<String, SecurityQuote> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, SecurityQuote> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
    
}
