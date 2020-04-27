/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.operation.controller;

import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import steps.dev.myfinance7.common.contract.OperationService;
import steps.dev.myfinance7.common.model.operation.Operation;
import steps.dev.myfinance7.operation.repository.OperationRepository;

/**
 *
 * @author stepin
 * @param <T>
 */


public abstract class OperationController implements OperationService{
    
    OperationRepository repository;
    
    @GetMapping("test")
    @Override
    public ResponseEntity<String> getTest(){
        return new ResponseEntity<>(this.getClass().getName(), HttpStatus.OK);
    }

    @GetMapping()
    @Override
    public ResponseEntity<List<Operation>> getList() {
        return ResponseEntity.ok(repository.findAll());
    }
    
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Operation> getById(@PathVariable("id") long id) {
        return new ResponseEntity<>(
                repository.findById(id).get(), 
                HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    @Override
    public ResponseEntity<Operation> post(@RequestBody Operation operation) {
        return new ResponseEntity<>(
                repository.save(operation),
                HttpStatus.CREATED);
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public void put(@RequestBody Operation operation) {
        repository.save(operation);
    }   

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Override
    public void delete(@PathVariable("id") long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
        }
    }
}
