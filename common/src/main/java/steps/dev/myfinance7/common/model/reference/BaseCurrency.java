/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.common.model.reference;

import java.io.Serializable;
import java.util.Currency;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author stepin
 */

@Data
@Entity
public class BaseCurrency implements Serializable {
    
    @Id
    @Column(
            columnDefinition="varchar(3) CHECK (currency in ('USD', 'EUR', 'RUB'))", 
            updatable = false, 
            insertable = false)
    private Currency currency;
    
}
