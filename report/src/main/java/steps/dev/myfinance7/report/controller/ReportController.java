/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.report.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import steps.dev.myfinance7.common.contract.ReportService;
import steps.dev.myfinance7.common.model.report.IPortfolioInstrumentReport;
import steps.dev.myfinance7.common.model.report.PortfolioInstrumentReportImpl;
import steps.dev.myfinance7.report.repository.PortfolioInstrumentReportRepository;

/**
 *
 * @author stepin
 */

@Controller
public class ReportController implements ReportService{
    
    @Autowired
    private PortfolioInstrumentReportRepository portfolioInstrumentReportRepository;
    
    //return ResponseEntity.ok(portfolioInstrumentReportRepository.report());

    @Override
    public ResponseEntity<List<PortfolioInstrumentReportImpl>> allPortfolioInstrumentsReport() {

        List<PortfolioInstrumentReportImpl> report = portfolioInstrumentReportRepository.report()
                .stream()
                .map(PortfolioInstrumentReportImpl::new)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(report);
    }
    

}
