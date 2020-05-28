/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.operation.consumer.client;

import org.springframework.cloud.openfeign.FeignClient;
import steps.dev.myfinance7.common.contract.OperationService;

/**
 *
 * @author stepin
 */

@FeignClient(name = "operation/")
public interface OperationClient extends OperationService{
    
}
