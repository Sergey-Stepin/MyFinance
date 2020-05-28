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

public class PortfolioInstrumentReportImpl implements IPortfolioInstrumentReport{

    private String portfolioName;
    
    private String instrumentName;
    
    private double longAmount;
    
    private double longCurrencyAmount;
    
    private double shortAmount;
    
    private double shortCurrencyAmount;
    
    private Currency currency;

    public PortfolioInstrumentReportImpl() {
    }
    
    public PortfolioInstrumentReportImpl(IPortfolioInstrumentReport iReport) {
        this.portfolioName = iReport.getPortfolioName();
        this.instrumentName = iReport.getInstrumentName();
        this.longAmount = iReport.getLongAmount();
        this.longCurrencyAmount = iReport.getLongCurrencyAmount();
        this.shortAmount = iReport.getShortAmount();
        this.shortCurrencyAmount = iReport.getShortCurrencyAmount();
        this.currency = iReport.getCurrency();
    }

//    public PortfolioInstrumentReportImpl(String portfolioName, String instrumentName, double longAmount, double longCurrencyAmount, double shortAmount, double shortCurrencyAmount, Currency currency) {
//        this.portfolioName = portfolioName;
//        this.instrumentName = instrumentName;
//        this.longAmount = longAmount;
//        this.longCurrencyAmount = longCurrencyAmount;
//        this.shortAmount = shortAmount;
//        this.shortCurrencyAmount = shortCurrencyAmount;
//        this.currency = currency;
//    }

    @Override
    public String toString() {
        return "PortfolioInstrumentReport{" + "portfolioName=" + portfolioName + ", instrumentName=" + instrumentName + ", longAmount=" + longAmount + ", longCurrencyAmount=" + longCurrencyAmount + ", shortAmount=" + shortAmount + ", shortCurrencyAmount=" + shortCurrencyAmount + ", currency=" + currency + '}';
    }
    
    @Override
    public String getPortfolioName() {
        return portfolioName;
    }

    @Override
    public String getInstrumentName() {
        return instrumentName;
    }

    @Override
    public double getLongAmount() {
        return longAmount;
    }

    @Override
    public double getLongCurrencyAmount() {
        return longCurrencyAmount;
    }

    @Override
    public double getShortAmount() {
        return shortAmount;
    }

    @Override
    public double getShortCurrencyAmount() {
        return shortCurrencyAmount;
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }
    
    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    public void setLongAmount(double longAmount) {
        this.longAmount = longAmount;
    }

    public void setLongCurrencyAmount(double longCurrencyAmount) {
        this.longCurrencyAmount = longCurrencyAmount;
    }

    public void setShortAmount(double shortAmount) {
        this.shortAmount = shortAmount;
    }

    public void setShortCurrencyAmount(double shortCurrencyAmount) {
        this.shortCurrencyAmount = shortCurrencyAmount;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
    
}
