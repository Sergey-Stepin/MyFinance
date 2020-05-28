/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.exchange.gate.config.moex;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author stepin
 */

@Configuration
@ConfigurationProperties(prefix = "receiver.moex.cuurecnies")
public class CurrenciesMoexReceiverConfig extends AbstractMoexReceiverConfig {
    
}
