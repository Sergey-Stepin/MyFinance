/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.exchange.gate.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import steps.dev.exchange.gate.client.InstrumentClient;
import steps.dev.myfinance7.common.model.exchange.ExchangeReceiverName;
import steps.dev.myfinance7.common.model.exchange.IExchangeReceiver;

/**
 *
 * @author stepin
 */
@Service
public class ExchangeGateService {

    @Autowired
    InstrumentClient instrumentClient;

    @Autowired
    Map<ExchangeReceiverName, IExchangeReceiver> receivers;

    @Scheduled(fixedRateString = "${gate.shedule-rate:5000}")
    public void shceduledStart() {

        System.out.println("Fixed rate task - " + System.currentTimeMillis() / 1000);
        debugReceiversPrint();

        receivers
                .entrySet()
                .forEach(entry -> receive(entry.getKey(), entry.getValue()));
    }

    private void debugReceiversPrint() {
        System.out.println("=== ALL RECEIVERS CONFIGURED =========================");
        receivers.entrySet()
                .stream()
                .forEach(entry -> System.out.println("### " + entry.getKey().name() + ":" + entry.getValue().getClass() + "; " + entry.getValue().toString()));
        System.out.println("======================================================");

    }

    private void receive(ExchangeReceiverName receiverName, IExchangeReceiver receiver) {

        System.out.println("###================================================================");
        System.out.println("### receiverName=" + receiverName.name() + " ; " + receiver);
        
        List<String> tickets = instrumentClient
                .getTicketsByExchangeReceiverName(receiverName.name())
                .getBody();

        receiver.updateByTickets(tickets);

    }

}
