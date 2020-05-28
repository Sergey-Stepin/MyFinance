/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.common.model.currency;

import java.io.Serializable;
import java.util.Currency;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author stepin
 */
@Data
@Entity
@Table(
        uniqueConstraints
        = @UniqueConstraint(
                name = "unq_currency_pair_date",
                columnNames = {"currency", "baseCurrency", "rateDate"}
        )
)
public class CurrencyRate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @NotNull
//    @Column(nullable = false, length = 3)
//    private Currency currency;
    
    @NotNull    
    @ManyToOne    
    @JoinColumn(name = "currency")
    private CurrencyItem currency;

    @NotNull
    @Column(nullable = false, length = 3)
    private Currency baseCurrency;

    @NotNull
    @Column(nullable = false)
    private double value;

    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date rateDate;
    
}
