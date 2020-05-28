/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.common.model.operation;

import java.io.Serializable;
import java.util.Currency;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.Data;
import steps.dev.myfinance7.common.model.instrument.Instrument;
import steps.dev.myfinance7.common.model.portfolio.Portfolio;


/**
 *
 * @author stepin
 */

@Data
@Embeddable
public class OperationPart implements Serializable {
    
    @ManyToOne(optional = true)
    @JoinColumn(name = "portfolio_id")
    //@NotNull
    private Portfolio portfolio;
    
    @ManyToOne(optional = true)
    @JoinColumn(name = "instrument_id")
    //@NotNull
    private Instrument instrument;
    
    private double amount;
    
    @NotNull
    @Column(nullable = true, length = 3)
    //@ManyToOne(optional = true)
    ///@JoinColumn(name = "currency")
    private Currency currency;
    
    private double currencyAmmount;
    
    private double currencyRate;    
    
}
