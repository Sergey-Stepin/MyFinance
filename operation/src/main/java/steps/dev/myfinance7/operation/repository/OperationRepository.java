/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.operation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import steps.dev.myfinance7.common.model.operation.Operation;

/**
 *
 * @author stepin
 */

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long>{
    
}
