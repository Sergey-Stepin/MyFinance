/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.currency.repository;

import java.time.ZonedDateTime;
import java.util.Currency;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import steps.dev.myfinance7.common.model.currency.CurrencyItem;
import steps.dev.myfinance7.common.model.currency.CurrencyRate;

/**
 *
 * @author stepin
 */
public interface CurrencyRepository extends JpaRepository<CurrencyItem, Currency> {

    public static final String GET_BASE_CURRENCY = "select currency from finance.base_currency";
    
    public static final String UPDATE_RATE_BY_TICKET
            = "insert into finance.currency_rate (currency, base_currency, value, rate_date) values(\n"
            + ":currency, \n"
            + ":baseCurrency,\n"
            + ":rateValue,\n"
            + ":rateDate \n"
            + ") on conflict on constraint unq_currency_pair_date do update set\n"
            + "currency = :currency, ,\n"
            + "base_currency = :baseCurrency, \n"
            + "value = :rateValue, \n"
            + "rate_date = :rateDate \n"
            ;

    public CurrencyItem findByTicket(String ticket);
    
    @Query(value = GET_BASE_CURRENCY, nativeQuery = true)    
    public Currency getBaseCurrency();
    
    @Transactional
    @Modifying
    @Query(value = UPDATE_RATE_BY_TICKET, nativeQuery = true)
    public void updateRatesByTickets(
            @Param("currency") String currencyCode,
            @Param("baseCurrency") String baseCurrency,
            @Param("rateValue") double rateValue,
            @Param("rateDate") Date rateDate);

}
