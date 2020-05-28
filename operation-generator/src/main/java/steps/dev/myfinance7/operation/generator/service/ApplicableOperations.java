/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.operation.generator.service;

import java.util.function.Predicate;
import steps.dev.myfinance7.common.model.operation.OperationType;
import static steps.dev.myfinance7.common.model.operation.OperationType.BOND_PURCHASING;
import static steps.dev.myfinance7.common.model.operation.OperationType.BOND_SELLING;
import static steps.dev.myfinance7.common.model.operation.OperationType.INTEREST_RECEIVING;
import static steps.dev.myfinance7.common.model.operation.OperationType.SHARE_PURCHASING;
import static steps.dev.myfinance7.common.model.operation.OperationType.SHARE_SELLING;

/**
 *
 * @author stepin
 */
public interface ApplicableOperations {
    
    public static final Predicate<OperationType> IS_BANK_ACCOUNT_OPERATION_TYPE 
            = operationType -> operationType.equals(INTEREST_RECEIVING);
    
    public static final Predicate<OperationType> IS_SHARE_OPERATION_TYPE 
            = operationType -> 
                        operationType.equals(SHARE_PURCHASING)
                        ||  operationType.equals(SHARE_SELLING);
    
    public static final Predicate<OperationType> IS_BOND_OPERATION_TYPE 
            = operationType -> 
                        operationType.equals(BOND_PURCHASING)
                        ||  operationType.equals(BOND_SELLING);
    
}
