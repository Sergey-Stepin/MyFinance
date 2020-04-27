/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.common.model.instrument;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Currency;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author stepin
 */
@Data
@Entity
public class Instrument implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long instrumentId;

    @NotNull
    @Column(nullable = false, unique = true)
    private String instrumentName;

    @NotNull
    @Column(nullable = false, length = 3)
    private Currency currency;

    @NotNull
    @Column(nullable = false)
    private double amount;
    
    @NotNull
    @Column(nullable = false)
    private double price;
    
    @NotNull
    @Column(nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Timestamp priceTimestamp;

    @NotNull
    @Column(nullable = false)
    private Date maturesAt;

}
