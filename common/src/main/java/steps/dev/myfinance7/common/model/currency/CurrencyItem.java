/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.common.model.currency;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Currency;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author stepin
 */

@Data
@Entity
public class CurrencyItem implements Serializable {
    
    @Id
    private Currency currency;
    
    private String ticket;
    
}
