/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.webclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import steps.dev.myfinance7.common.model.portfolio.Portfolio;
import steps.dev.myfinance7.webclient.client.OperationClient;
import steps.dev.myfinance7.webclient.client.PortfolioClient;

/**
 *
 * @author stepin
 */

@Controller
//@SessionAttributes({"portfolio"})
@RequestMapping("/portfolio")
public class PortfolioController {
    
    @Autowired
    private PortfolioClient portfolioClient;
    
   @GetMapping("/list")
    public String getList(Model model){
        
        model.addAttribute("portoflios", portfolioClient.getAll().getBody());
        return "portfolio/list";
    }
    
    @GetMapping("delete/{porrtfolioId}")
    public String delete(@PathVariable("porrtfolioId") long porrtfolioId){
        portfolioClient.delete(porrtfolioId);
        return "redirect:/portfolio/list";
    }

    @GetMapping("/add")
    public String add(
            Model model) {
        
        System.out.println("ADD");
        model.addAttribute("portfolio", new Portfolio());
        return "portfolio/edit";
    }

    @GetMapping("/edit/{porrtfolioId}")
    public String edit(
            @PathVariable("porrtfolioId") long porrtfolioId,
            Model model) {
        
        Portfolio portfolio = portfolioClient.getById(porrtfolioId).getBody();
        model.addAttribute("portfolio", portfolio);
        
        return "portfolio/edit";
    }
    
    @GetMapping("/operations/{porrtfolioId}")
    public String getPortfolioOperations(
            @PathVariable("porrtfolioId") long porrtfolioId,
            Model model){
        
        System.out.println("### PORTFOLIO OPERATION:");

        Portfolio portfolio = portfolioClient.getById(porrtfolioId).getBody();
        model.addAttribute("portfolio", portfolio);
        
        return "/portfolio/portfolio_operations";
    }
    
    @PostMapping(params = "_save")
    public String post(
            Portfolio portfolio) {

        portfolioClient.post(portfolio);
        return "redirect:/portfolio/list";
    }

    @PostMapping(params = "_cancel")
    public String cancel(
            Portfolio portfolio,
            SessionStatus status) {

        status.setComplete();
        return "redirect:/portfolio/list";
    }
    
}
