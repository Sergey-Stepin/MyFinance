/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.myfinance7.operation.generator.service;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import steps.dev.myfinance7.common.model.instrument.Instrument;
import steps.dev.myfinance7.common.model.instrument.InstrumentType;
import static steps.dev.myfinance7.common.model.instrument.InstrumentType.BANK_ACCOUNT;
import static steps.dev.myfinance7.common.model.instrument.InstrumentType.BOND;
import static steps.dev.myfinance7.common.model.instrument.InstrumentType.CASH_ON_HANDS;
import static steps.dev.myfinance7.common.model.instrument.InstrumentType.SHARE;
import steps.dev.myfinance7.common.model.operation.Operation;
import steps.dev.myfinance7.common.model.operation.OperationPart;
import steps.dev.myfinance7.common.model.operation.OperationType;
import steps.dev.myfinance7.common.model.portfolio.Portfolio;
import steps.dev.myfinance7.operation.generator.client.InstrumentClient;
import steps.dev.myfinance7.operation.generator.client.PortfolioClient;

/**
 *
 * @author stepin
 */
@Component
public class RandomOperationGenerator {

    @Autowired
    private InstrumentClient instrumentClient;
    
    @Autowired
    private PortfolioClient portfolioClient;

    private static final Random RND = new Random();

    private static final InstrumentType[] APPLICABLE_INSTRUMENT_TYPES = {
        BANK_ACCOUNT,
        BOND,
        SHARE
    };

    private static final List<Currency> CURRENCIES_LIST = Currency.getAvailableCurrencies()
            .stream()
            .collect(Collectors.toList());

    public Operation generate() {

        List<Portfolio> portfolios = portfolioClient.getAll().getBody();
        int portfolioNum = RND.nextInt(portfolios.size());
        Portfolio portfolio = portfolios.get(portfolioNum);
        
        List<Instrument> applicableInstruments = getApplicableInstruments();
        Instrument moneyInstrument = getMoneyInstrument(applicableInstruments);

        int instrumentNum = RND.nextInt(applicableInstruments.size());
        Instrument mainInstrument = applicableInstruments.get(instrumentNum);

        OperationType operationType = randomOperationType(mainInstrument);

        Operation operation;
        switch (operationType) {
            case INTEREST_RECEIVING:
                operation = createInterestReceiving(portfolio, mainInstrument);
                break;

            case SHARE_PURCHASING:
            case BOND_PURCHASING:
                operation = createBuyOperation(portfolio, mainInstrument, moneyInstrument, operationType);
                break;

            case SHARE_SELLING:
            case BOND_SELLING:
                operation = createSellOperation(portfolio, mainInstrument, moneyInstrument, operationType);
                break;

            default:
                throw new UnsupportedOperationException("Generated operation type :" + operationType.name());
        }

        return operation;
    }

    private List<Instrument> getApplicableInstruments() {
        return instrumentClient.getAll().getBody()
                .stream()
                .filter(this::isInstrumentApplicable)
                .collect(Collectors.toList());
    }
    
    private boolean isInstrumentApplicable(Instrument instrument){
        return Stream.of(APPLICABLE_INSTRUMENT_TYPES)
                .anyMatch(instrumentType -> instrument.getInstrumentType().equals(instrumentType));
    }

    private Instrument getMoneyInstrument(List<Instrument> applicableInstruments) {
        return applicableInstruments.stream()
                .filter(instrument -> instrument.getInstrumentType().equals(InstrumentType.BANK_ACCOUNT))
                .findAny()
                .get();
    }

    private OperationType randomOperationType(Instrument instrument) {

        List<OperationType> applicableOperationTypes;

        switch (instrument.getInstrumentType()) {

            case BANK_ACCOUNT:
                applicableOperationTypes = Stream.of(OperationType.values())
                        .filter(ApplicableOperations.IS_BANK_ACCOUNT_OPERATION_TYPE)
                        .collect(Collectors.toList());
                break;

            case SHARE:
                applicableOperationTypes = Stream.of(OperationType.values())
                        .filter(ApplicableOperations.IS_SHARE_OPERATION_TYPE)
                        .collect(Collectors.toList());
                break;

            case BOND:
                applicableOperationTypes = Stream.of(OperationType.values())
                        .filter(ApplicableOperations.IS_BOND_OPERATION_TYPE)
                        .collect(Collectors.toList());
                break;

            default:
                throw new UnsupportedOperationException("Generated operations for instrument type :" + instrument.getInstrumentType() + " is not supported");
        }

        return randomOperationTypeFromList(applicableOperationTypes);
    }

    private OperationType randomOperationTypeFromList(List<OperationType> applicableOperationTypes) {
        int operationTypeNum = RND.nextInt(applicableOperationTypes.size());
        return applicableOperationTypes.get(operationTypeNum);
    }

    private Operation createInterestReceiving(Portfolio portfolio, Instrument instrument) {
        Operation operation = new Operation();
        operation.setOpertaionType(OperationType.INTEREST_RECEIVING);
        operation.setOperationComment("Interest receiving");

        double amount = RND.nextDouble() * 10;
        double currencyAmount = amount * RND.nextDouble() * 10;

        OperationPart longOperationPart = new OperationPart();
        longOperationPart.setPortfolio(portfolio);
        longOperationPart.setInstrument(instrument);
        longOperationPart.setCurrency(instrument.getCurrency());
        longOperationPart.setCurrencyRate(amount / currencyAmount);
        longOperationPart.setCurrencyAmmount(currencyAmount);
        longOperationPart.setAmount(amount);

        operation.setLongPart(longOperationPart);

        return operation;
    }

    private Operation createBuyOperation(
            Portfolio portfolio,
            Instrument instrument, 
            Instrument moneyInstrument, 
            OperationType operationType) {
        
        Operation operation = new Operation();
        operation.setOpertaionType(operationType);
        operation.setOperationComment("Buy ");

        double amount = RND.nextDouble() * 100;
        double buyAmount = amount * RND.nextDouble() / 10;
        double moneyAmount = amount * RND.nextDouble() * 10;

        OperationPart securityOperationPart = new OperationPart();
        securityOperationPart.setPortfolio(portfolio);
        securityOperationPart.setInstrument(instrument);
        securityOperationPart.setCurrency(instrument.getCurrency());
        securityOperationPart.setCurrencyRate(amount / buyAmount);
        securityOperationPart.setCurrencyAmmount(buyAmount);
        securityOperationPart.setAmount(amount);

        OperationPart money = new OperationPart();
        money.setPortfolio(portfolio);
        money.setInstrument(moneyInstrument);
        money.setCurrency(moneyInstrument.getCurrency());
        money.setCurrencyRate(amount / moneyAmount);
        money.setCurrencyAmmount(moneyAmount);
        money.setAmount(amount);

        operation.setLongPart(securityOperationPart);
        operation.setShortPart(money);

        return operation;
    }

    private Operation createSellOperation(
            Portfolio portfolio,
            Instrument instrument, 
            Instrument moneyInstrument, OperationType operationType) {
        
        Operation operation = new Operation();
        operation.setOpertaionType(operationType);
        operation.setOperationComment("Sell");

        double amount = RND.nextDouble() * 100;
        double buyAmount = amount * RND.nextDouble() / 10;
        double moneyAmount = amount * RND.nextDouble() * 10;

        OperationPart securityOperationPart = new OperationPart();
        securityOperationPart.setPortfolio(portfolio);
        securityOperationPart.setInstrument(instrument);
        securityOperationPart.setCurrency(instrument.getCurrency());
        securityOperationPart.setCurrencyRate(amount / buyAmount);
        securityOperationPart.setCurrencyAmmount(buyAmount);
        securityOperationPart.setAmount(amount);

        OperationPart money = new OperationPart();
        money.setPortfolio(portfolio);
        money.setInstrument(moneyInstrument);
        money.setCurrency(moneyInstrument.getCurrency());
        money.setCurrencyRate(amount / moneyAmount);
        money.setCurrencyAmmount(moneyAmount);
        money.setAmount(amount);

        operation.setLongPart(money);
        operation.setShortPart(securityOperationPart);

        return operation;
    }

}
