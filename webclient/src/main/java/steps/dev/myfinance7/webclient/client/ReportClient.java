/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.webclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import steps.dev.myfinance7.common.contract.ReportService;

/**
 *
 * @author stepin
 */

@FeignClient(name = "report")
public interface ReportClient extends ReportService{
    
}
