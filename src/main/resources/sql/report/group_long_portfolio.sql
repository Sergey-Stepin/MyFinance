SELECT 
portfolio_name,
instrument_name,
SUM(long_amount) AS long_amount_sum,
SUM(long_currency_amount) AS long_currency_amount_sum,
long_currency
--operation_id, long_amount, long_currency, long_currency_amount, long_currency_rate, operation_comment, operation_timestamp, opertaion_type, payment_date, short_amount, short_currency, short_currency_amount, short_currency_rate, long_instrument_id, long_portfolio_id, short_instrument_id, short_portfolio_id
	FROM finance.operation_journal AS operations
	LEFT JOIN finance.instrument AS instrument ON instrument.instrument_id = operations.long_instrument_id
	LEFT JOIN finance.portfolio AS portfolio ON portfolio.portfolio_id = operations.long_portfolio_id
        WHERE operations.long_instrument_id IS NOT NULL
	GROUP BY portfolio_name, instrument_name, long_currency
        ORDER BY portfolio_name, instrument_name
