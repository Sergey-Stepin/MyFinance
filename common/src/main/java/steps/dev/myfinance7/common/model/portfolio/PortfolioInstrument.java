/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.common.model.portfolio;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.Data;
import steps.dev.myfinance7.common.model.instrument.Instrument;

/**
 *
 * @author stepin
 */

//@Data
//@Entity
//public class PortfolioInstrument implements Serializable {
//    
//    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    private long id;
//    
//    @ManyToOne(optional = false)
//    @JoinColumn(name = "portfolio_id")
//    @NotNull
//    private Portfolio portfolio;
//
//    @ManyToOne(optional = false)
//    @JoinColumn(name = "instrument_id")
//    @NotNull
//    private Instrument instrument;
//    
//    private double amount;
//
//}
