/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.webclient.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import steps.dev.myfinance7.common.model.report.IPortfolioInstrumentReport;
import steps.dev.myfinance7.common.model.report.PortfolioInstrumentReportImpl;
import steps.dev.myfinance7.webclient.client.ReportClient;

/**
 *
 * @author stepin
 */

@Controller
@RequestMapping("/report")
public class ReportController {
    
    @Autowired
    private ReportClient reportClient;

    @GetMapping("/")
    public String reportsList(){
        return "report/reports";
    }
    
    @GetMapping("all_portfolio_intstruments_report")
    public String allPortfolioInstrumentsReport(Model model){
        model.addAttribute("report", reportClient.allPortfolioInstrumentsReport().getBody());
        
        return "report/portfolio_intstruments_report";
    }
}
