/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.common.model.exchange;

import java.util.List;

/**
 *
 * @author stepin
 */

public interface IExchangeReceiver {
    
//    private final ExchangeReceiverType exchangeReceiverType;
//
//    public AbstractExchangeReceiverService(ExchangeReceiverType exchangeReceiverType) {
//        this.exchangeReceiverType = exchangeReceiverType;
//    }
//    
    public abstract void updateByTickets(List<String> tickets);
    
}
