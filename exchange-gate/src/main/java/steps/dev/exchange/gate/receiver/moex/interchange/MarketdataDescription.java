/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.exchange.gate.receiver.moex.interchange;

import lombok.Data;

/**
 *
 * @author stepin
 */

@Data
public class MarketdataDescription {
    
    private int secidColumnNum;
    private int lastColumnNum;
    private int timeColumnNum;
    private int systimeColumnNum;
    private int updatetimeColumnNum;
    
}
