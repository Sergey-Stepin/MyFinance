/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.common.model.exchange;

import java.io.Serializable;
import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import lombok.Data;

/**
 *
 * @author stepin
 */

//@Data
//@Entity
//public class ExchangeReceiverDescriptor implements Serializable {
//    
//    @Id
//    //private String receiverName;
//    @Enumerated(EnumType.STRING)
//    private ExchangeReceiverType exchangeReceiverType;
//    
//    @ElementCollection
//    @CollectionTable(name = "exchange_receivers_parameters")
//    @MapKeyColumn(name = "parameter_name")
//    @Column(name = "parameter_value")
//    private Map<String, String> parameters;
//    
//}
