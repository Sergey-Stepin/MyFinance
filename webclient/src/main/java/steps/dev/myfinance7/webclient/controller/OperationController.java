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
import steps.dev.myfinance7.common.model.operation.Operation;
import steps.dev.myfinance7.common.model.operation.Operation;
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
public class OperationController {
    
    @Autowired
    private OperationClient operationClient;
    
    @ModelAttribute("operationTypes")
    public List<OperationType> populateAvailableOperationTypes(){
        return Arrays.asList(OperationType.values());
    }
    
    @GetMapping("/list")
    public String getList(Model model){
        model.addAttribute("operations", operationClient.getList().getBody());
        return "operation/list";
    }
    
    @GetMapping("/add")
    public String getAdd(Model model) {
        
        model.addAttribute("operation", new Operation());
        return "/operation/edit";
    }

    @GetMapping("/edit/{operationId}")
    public String getEdit(
            @PathVariable("operationId") long operationId,
            Model model) {
        
        Operation operation = operationClient.getById(operationId).getBody();
        System.out.println("### Operation=" + operation);
        model.addAttribute("operation", operation);
        return "/operation/edit";
    }
    
    @PostMapping("/edit")
    public String postEdit(Operation operation) {
        
        operationClient.post(operation);
        return "redirect:/operation/list";
    }
    
    @GetMapping("/delete/{id}")
    public String getDelete(@PathVariable long id) {
        
        operationClient.delete(id);
        return "redirect:/operation/list";
    }
    
}
