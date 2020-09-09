# MyFinance
Project for studing and testig purposes.

# Subjects:
- Srping
- Microservices, 
- Service-Discovery,
- Feign,
- JPA,
- Thymeleaf,
- Kafka,
- Chatbots

# Modules:

. Common
Packages of common classes such as: cmodels, contracts, utils etc.

.Eureca
Service-Discovery

. Instruments
RESTful back-end web-service for financial instruments
Receives and updates market quotes throw Kafka

. Portfolio
RESTful back-end web-service for financial portfolios of instruments

. Operations
RESTful back-end web-service for financial operations with instruments

. Currency
RESTful back-end web-service for currency rates
Receives and updates market quotes throw Kafka

. WebClient
Web-server (Thymeleaf + Spring)
Eureca + Feign is used for communication with the backends

.Exchange-gate
Integrates the system with financial-exchanges
Implemented integration with MOEX (http://moex.ru/): gets stocks- bonds- and currency-rates and updates the rates in the system through Kafka

. Report
RESTful back-end web-service for financial reports

. Telegram
Telegrambot-integration: sends updated stocks- bonds- and currency-rates to a Telegram-chat









