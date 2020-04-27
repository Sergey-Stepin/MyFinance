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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import steps.dev.myfinance7.common.model.operation.Operation;

/**
 *
 * @author stepin
   */

@RequestMapping(path="/operation", produces = "application/json")
public interface OperationService{

    @GetMapping("test")
    public ResponseEntity<String> getTest();
    
    @GetMapping
    public ResponseEntity<List<Operation>> getList();
    
    @GetMapping("/{id}")
    public ResponseEntity<Operation> getById(@PathVariable("id") long id) ;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Operation> post(@RequestBody Operation operation) ;

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void put(@RequestBody Operation operation) ;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) ;
    
}
