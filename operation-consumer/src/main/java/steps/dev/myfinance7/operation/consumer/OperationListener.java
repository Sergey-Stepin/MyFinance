/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.operation.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import steps.dev.myfinance7.common.model.operation.Operation;
import steps.dev.myfinance7.operation.consumer.client.OperationClient;

/**
 *
 * @author stepin
 */

@Service
public class OperationListener {

    @Autowired
    private OperationClient operationClient;
    
    @KafkaListener (topics = "${consumer.kafka.topic}" , groupId = "${consumer.kafka.group}")
    public void listen(Operation operation) {
        System.out.println("##### OPERATION: " + operation);
        
        operationClient.put(operation);
        
    }
}
