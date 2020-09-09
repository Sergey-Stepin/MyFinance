# MyFinance
Project for studding and testing purposes.

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

1. Common
Packages of common classes such as: models, contracts, utils etc.

2. Eureca
Service-Discovery

3. Instruments
RESTful back-end web-service for financial instruments
Receives and updates market quotes throw Kafka

4. Portfolio
RESTful back-end web-service for financial portfolios of instruments

5. Operations
RESTful back-end web-service for financial operations with instruments

6. Currency
RESTful back-end web-service for currency rates
Receives and updates market quotes throw Kafka

7. WebClient
Web-server (Thymeleaf + Spring)
Eureca + Feign is used for communication with the backends

8. Exchange-gate
Integrates the system with financial-exchanges
Implemented integration with MOEX (http://moex.ru/): gets stocks- bonds- and currency-rates and updates the rates in the system through Kafka

9. Report
RESTful back-end web-service for financial reports

10. Telegram
Telegrambot-integration: sends updated stocks- bonds- and currency-rates to a Telegram-chat
