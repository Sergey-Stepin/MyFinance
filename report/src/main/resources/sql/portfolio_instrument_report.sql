SELECT 
portfolio_name,
instrument_name,
SUM(long_amount_sum) AS total_long_amount,
SUM(long_currency_amount_sum) AS total_long_currency_amount,
SUM(short_amount_sum) AS total_short_amount,
SUM(short_currency_amount_sum) AS total_short_currency_amount,
currency

FROM (SELECT 
portfolio_name,
instrument_name,
SUM(long_amount) AS long_amount_sum,
SUM(long_currency_amount) AS long_currency_amount_sum,
0.0 AS short_amount_sum,
0.0 AS short_currency_amount_sum,
long_currency AS currency
	FROM finance.operation_journal AS operations
	LEFT JOIN finance.instrument AS instrument ON instrument.instrument_id = operations.long_instrument_id
	LEFT JOIN finance.portfolio AS portfolio ON portfolio.portfolio_id = operations.long_portfolio_id
        WHERE operations.long_instrument_id IS NOT NULL
	GROUP BY portfolio_name, instrument_name, long_currency
--        ORDER BY portfolio_name, instrument_name

UNION

SELECT 
portfolio_name,
instrument_name,
0.0 AS long_amount_sum,
0.0 AS long_currency_amount_sum,
SUM(short_amount) AS short_amount_sum,
SUM(short_currency_amount) AS short_currency_amount_sum,
short_currency
	FROM finance.operation_journal AS operations
	LEFT JOIN finance.instrument AS instrument ON instrument.instrument_id = operations.short_instrument_id 
	LEFT JOIN finance.portfolio AS portfolio ON portfolio.portfolio_id = operations.short_portfolio_id
        WHERE operations.short_instrument_id IS NOT NULL        
        GROUP BY portfolio_name, instrument_name, short_currency
) 
AS splitted_by_long_short
GROUP BY portfolio_name, instrument_name, currency
ORDER BY portfolio_name, instrument_name
