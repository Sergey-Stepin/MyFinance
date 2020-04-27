/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.webclient.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import steps.dev.myfinance7.common.model.operation.OperationType;
import steps.dev.myfinance7.common.model.portfolio.Portfolio;
import steps.dev.myfinance7.webclient.client.OperationClient;
import steps.dev.myfinance7.webclient.client.PortfolioClient;

/**
 *
 * @author stepin
 */

@Controller
@RequestMapping("/operation")
@SessionAttributes({"portfolio"})
public class OperationController {
    
    @Autowired
    private OperationClient client;
    
    @Autowired
    private PortfolioClient portfolioClient;
    
    @ModelAttribute("operationTypes")
    public List<OperationType> populateAvailableOperationTypes(){
        return Arrays.asList(OperationType.values());
    }
    
    @GetMapping("/portfolio/{portfolioId}")
    public String getPortfolioOperations(
            @PathVariable long portfolioId,
            Model model){
        
        model.addAttribute("portfolio",portfolioClient.getById(portfolioId).getBody());
        return "/portfolio/portfolio_operations";
    }
    
    @GetMapping("/choose_type")
    public String getOperationTypes(@ModelAttribute("portfolio") Portfolio portfolio){
        return "operation/choose_type";
    }

    @PostMapping(path = "/choose_type", params = "_add")
    public String postOperationTypes(
            @RequestParam String operationType,
            @ModelAttribute("portfolio") Portfolio portfolio){
        
        OperationType type = OperationType.valueOf(operationType);
        switch (type) {
            
            case BANK_ACCOUNT_OPENNING:
                return "redirect:/operation/bank_account_openning/add/" + portfolio.getPortfolioId();

            case BANK_ACCOUNT_CLOSING:
                return "redirect:/operation/bank_account_closing/add";

            case INTEREST_RECEIVING:
                return "redirect:/operation/interest_receiving/add";
                
            case BOND_PURCHASING:
            case BOND_SELLING:
            case BOND_AMORTIZATION:    
            default:
                throw new IllegalArgumentException("Cannot create an operation for OperationType: " + type);

        }
        
    }
    
    
}
