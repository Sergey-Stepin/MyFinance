/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.report.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import steps.dev.myfinance7.common.model.portfolio.Portfolio;
import steps.dev.myfinance7.common.model.report.IPortfolioInstrumentReport;

/**
 *
 * @author stepin
 */
@Repository
public interface PortfolioInstrumentReportRepository extends JpaRepository<Portfolio, Long> {

    public static final String ALL_PORTFOLION_INSTRUMENT_REPORT_QUERY = "SELECT \n"
            + "portfolio_name AS portfolioName,\n"
            + "instrument_name AS instrumentName,\n"
            + "SUM(long_amount_sum) AS longAmount,\n"
            + "SUM(long_currency_amount_sum) AS longCurrencyAmount,\n"
            + "SUM(short_amount_sum) AS shortAmount,\n"
            + "SUM(short_currency_amount_sum) AS shortCurrencyAmount,\n"
            + "currency\n"
            + "\n"
            + "FROM (SELECT \n"
            + "portfolio_name,\n"
            + "instrument_name,\n"
            + "SUM(long_amount) AS long_amount_sum,\n"
            + "SUM(long_currency_amount) AS long_currency_amount_sum,\n"
            + "0.0 AS short_amount_sum,\n"
            + "0.0 AS short_currency_amount_sum,\n"
            + "long_currency AS currency\n"
            + "	FROM finance.operation_journal AS operations\n"
            + "	LEFT JOIN finance.instrument AS instrument ON instrument.instrument_id = operations.long_instrument_id\n"
            + "	LEFT JOIN finance.portfolio AS portfolio ON portfolio.portfolio_id = operations.long_portfolio_id\n"
            + "        WHERE operations.long_instrument_id IS NOT NULL\n"
            + "	GROUP BY portfolio_name, instrument_name, long_currency\n"
            + "--        ORDER BY portfolio_name, instrument_name\n"
            + "\n"
            + "UNION\n"
            + "\n"
            + "SELECT \n"
            + "portfolio_name,\n"
            + "instrument_name,\n"
            + "0.0 AS long_amount_sum,\n"
            + "0.0 AS long_currency_amount_sum,\n"
            + "SUM(short_amount) AS short_amount_sum,\n"
            + "SUM(short_currency_amount) AS short_currency_amount_sum,\n"
            + "short_currency\n"
            + "	FROM finance.operation_journal AS operations\n"
            + "	LEFT JOIN finance.instrument AS instrument ON instrument.instrument_id = operations.short_instrument_id \n"
            + "	LEFT JOIN finance.portfolio AS portfolio ON portfolio.portfolio_id = operations.short_portfolio_id\n"
            + "        WHERE operations.short_instrument_id IS NOT NULL        \n"
            + "        GROUP BY portfolio_name, instrument_name, short_currency\n"
            + ") \n"
            + "AS splitted_by_long_short\n"
            + "GROUP BY portfolio_name, instrument_name, currency\n"
            + "ORDER BY portfolio_name, instrument_name";

    @Query(value = ALL_PORTFOLION_INSTRUMENT_REPORT_QUERY, nativeQuery = true)
    public List<IPortfolioInstrumentReport> report();
}
