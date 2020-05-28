/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.operation.generator.service;

import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import steps.dev.myfinance7.common.model.operation.Operation;

/**
 *
 * @author stepin
 */

@Service
public class OperationGeneratorService{
    
    @Value("${generator.kafka.topic}")
    private String kafkaTopic;

    @Autowired
    private KafkaTemplate<String, Operation> kafkaTemplate;

    public void send(Operation operation) {
        ListenableFuture<SendResult<String, Operation>> future = kafkaTemplate.send(kafkaTopic, operation);

        try {
            System.out.println(future.get().getProducerRecord().timestamp() + " : " + future.get().getProducerRecord().value());
        } catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }
    }
    
}
