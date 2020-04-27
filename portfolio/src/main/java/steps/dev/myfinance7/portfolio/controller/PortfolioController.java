/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.portfolio.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import steps.dev.myfinance7.common.contract.PortfolioService;
import steps.dev.myfinance7.common.model.portfolio.Portfolio;
import steps.dev.myfinance7.portfolio.repository.PortfolioRepository;

/**
 *
 * @author stepin
 */

@Controller
@RequestMapping("/portfolio")
public class PortfolioController implements PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;
    
//    @Autowired
//    private PortfolioInstrumentRepository portfolioInstrumentRepository;

    @Override
    @GetMapping    
    public ResponseEntity<List<Portfolio>> getAll() {
        return new ResponseEntity<>(portfolioRepository.findAll(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")    
    public ResponseEntity<Portfolio> getById(@PathVariable("id") Long id) {
       return new ResponseEntity<>(portfolioRepository.findById(id).get(), HttpStatus.OK);
    }

    @Override
    @PostMapping(consumes = "application/json")    
    public ResponseEntity<Portfolio> post(@RequestBody Portfolio portfolio) {
        return new ResponseEntity<>(portfolioRepository.save(portfolio), HttpStatus.CREATED);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping()
    public void put(@RequestBody Portfolio portfolio) {
        portfolioRepository.save(portfolio);
    }

    @Override
    @PatchMapping(path = "/{id}", consumes = "application/json")    
    public ResponseEntity<Portfolio> patch(
            @PathVariable("id") Long id,
            @RequestBody Portfolio patch) {

        Portfolio portfolio;
        try {
            portfolio = portfolioRepository.findById(id).get();
        } catch (EmptyResultDataAccessException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        if (patch.getPortfolioName() != null) {
            portfolio.setPortfolioName(patch.getPortfolioName());
        }

        return new ResponseEntity<>(portfolioRepository.save(portfolio), HttpStatus.OK);
    }
    
    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        try {
            portfolioRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    @GetMapping("/portfolio_insrtument/{id}")
//    public ResponseEntity<PortfolioInstrument> getPortfolioInstrumentById(@PathVariable("id") Long id) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override    
//    @PostMapping(path="/portfolio_insrtument/", consumes = "application/json")
//    public ResponseEntity<PortfolioInstrument> postPortfolioInstrument(@RequestBody PortfolioInstrument portfolioInstrument) {
//        return new ResponseEntity<>(portfolioInstrumentRepository.save(portfolioInstrument), HttpStatus.CREATED);
//    }
//
//    @Override
//    @PutMapping("/portfolio_insrtument/")
//    @ResponseStatus(HttpStatus.OK)
//    public void putPortfolioInstrument(@RequestBody PortfolioInstrument portfolioInstrument) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    @DeleteMapping("/portfolio_insrtument/")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deletePortfolioInstrument(@PathVariable("id") Long id) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
