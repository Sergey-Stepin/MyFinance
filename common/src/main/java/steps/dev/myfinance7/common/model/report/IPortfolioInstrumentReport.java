/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.common.model.report;

import java.util.Currency;
import lombok.Data;

/**
 *
 * @author stepin
 */

public interface IPortfolioInstrumentReport {

//    private String portfolioName;
//    
//    private String instrumentName;
//    
//    private double longAmount;
//    
//    private double longCurrencyAmount;
//    
//    private double shortAmount;
//    
//    private double shortCurrencyAmount;
//    
//    private Currency currency;
    
    
    public String getPortfolioName();

    public String getInstrumentName();

    public double getLongAmount();

    public double getLongCurrencyAmount();

    public double getShortAmount();

    public double getShortCurrencyAmount();

    public Currency getCurrency();
    
}
