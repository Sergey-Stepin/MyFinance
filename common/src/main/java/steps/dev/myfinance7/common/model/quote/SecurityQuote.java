/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.common.model.quote;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.ZonedDateTime;
import lombok.Data;

/**
 *
 * @author stepin
 */

@Data
public class SecurityQuote {
    
    private String ticket;
    
    private double price;
    
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    //private ZonedDateTime timestamp;
    
    private String datetime;
    
}
