/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.common.model.operation;

/**
 *
 * @author stepin
 */
public enum OperationType {
    
    BANK_ACCOUNT_OPENNING,
    BANK_ACCOUNT_CLOSING,
    INTEREST_RECEIVING,
    
    BOND_PURCHASING,
    BOND_SELLING,
    BOND_AMORTIZATION,
    
    SHARE_PURCHASING,
    SHARE_SELLING,
    SHARE_REDEMPTION,
    
    ETF_PURCHASING,
    ETF_SELLING,
    
    FEES_AND_COMISSIONS_PAIMENT,
    TAXES,
    
    PORTFOLIO_DEPOSITION, //Deposit valuess(cash/instruments) into portfolio
    PORTFOLIO_WITHDROWAL, //Depositwithdrow values(cash/instruments) from portfolio

}
