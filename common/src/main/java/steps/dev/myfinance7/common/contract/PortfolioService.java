/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.common.contract;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import steps.dev.myfinance7.common.model.portfolio.Portfolio;

/**
 *
 * @author stepin
 */

@RequestMapping(path="/portfolio", produces = "application/json")
public interface PortfolioService {
    
    @GetMapping
    public ResponseEntity<List<Portfolio>> getAll();
    
    @GetMapping("/{id}")
    public ResponseEntity<Portfolio> getById(@PathVariable("id") Long id) ;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Portfolio> post(@RequestBody Portfolio portfolio) ;

    @ResponseStatus(HttpStatus.OK)
    @PutMapping()
    public void put(@RequestBody Portfolio portfolio) ;

    @PatchMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<Portfolio> patch(
            @PathVariable("id") Long id,
            @RequestBody Portfolio patch) ;

    @DeleteMapping("/portfolio_insrtument/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) ;
    
//    @GetMapping("/portfolio_insrtument/{id}")
//    public ResponseEntity<PortfolioInstrument> getPortfolioInstrumentById(@PathVariable("id") Long id) ;
//
//    @PostMapping(path="/portfolio_insrtument/", consumes = "application/json")
//    public ResponseEntity<PortfolioInstrument> postPortfolioInstrument(@RequestBody PortfolioInstrument portfolioInstrument) ;
//
//    @PutMapping("/portfolio_insrtument/")
//    @ResponseStatus(HttpStatus.OK)
//    public void putPortfolioInstrument(@RequestBody PortfolioInstrument portfolioInstrument) ;
//    
//    @DeleteMapping("/portfolio_insrtument/")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deletePortfolioInstrument(@PathVariable("id") Long id) ;
    
}
