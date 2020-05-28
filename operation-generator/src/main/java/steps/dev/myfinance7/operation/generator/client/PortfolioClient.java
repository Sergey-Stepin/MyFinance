/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.operation.generator.client;

import org.springframework.cloud.openfeign.FeignClient;
import steps.dev.myfinance7.common.contract.PortfolioService;

/**
 *
 * @author stepin
 */

@FeignClient(name = "portfolio")
public interface PortfolioClient extends PortfolioService{
    
}
