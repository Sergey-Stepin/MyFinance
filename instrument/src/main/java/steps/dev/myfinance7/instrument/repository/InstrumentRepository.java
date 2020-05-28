/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.instrument.repository;

import java.time.ZonedDateTime;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import steps.dev.myfinance7.common.model.exchange.ExchangeReceiverName;
import steps.dev.myfinance7.common.model.instrument.Instrument;
import steps.dev.myfinance7.common.model.quote.SecurityQuote;

/**
 *
 * @author stepin
 */
public interface InstrumentRepository extends JpaRepository<Instrument, Long> {

//    public static final String UPDATE_PRICES_BY_TICKETS
//            = "update finance.instrument set\n"
//            + "price = ?1, \n"            
//            + "price_timestamp = ?2 \n"
//            + "where ticket = ?3";

    public static final String UPDATE_PRICES_BY_TICKETS
            = "update Instrument set\n"
            + "price = :price, \n"
            + "price_timestamp = :priceTimestamp \n"
            + "where ticket = :ticket";
    
    public List<Instrument> findByExchangeReceiverName(ExchangeReceiverName exchangeReceiverName);

    @Transactional
    @Modifying
    @Query(value = UPDATE_PRICES_BY_TICKETS)
    public void updatePricesByTickets(
            @Param("price") double price, 
            @Param("priceTimestamp") ZonedDateTime priceTimestamp, 
            @Param("ticket") String ticket);

}
