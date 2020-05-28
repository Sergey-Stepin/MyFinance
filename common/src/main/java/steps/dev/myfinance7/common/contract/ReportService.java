/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.common.contract;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import steps.dev.myfinance7.common.model.report.IPortfolioInstrumentReport;
import steps.dev.myfinance7.common.model.report.PortfolioInstrumentReportImpl;

/**
 *
 * @author stepin
   */

@RequestMapping(path="/report", produces = "application/json")
public interface ReportService{

    @GetMapping("/all_portfolion_instruments")
    public ResponseEntity<List<PortfolioInstrumentReportImpl>> allPortfolioInstrumentsReport();
}
