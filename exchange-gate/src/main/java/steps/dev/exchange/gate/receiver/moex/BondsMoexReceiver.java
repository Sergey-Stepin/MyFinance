/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steps.dev.exchange.gate.receiver.moex;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.StringUtils;
import steps.dev.exchange.gate.config.moex.AbstractMoexReceiverConfig;
import org.springframework.web.client.RestTemplate;
import steps.dev.exchange.gate.client.InstrumentClient;
import static steps.dev.exchange.gate.receiver.moex.AbstractMoexReceiver.MOEX_TIME_FORMATTER;
import static steps.dev.exchange.gate.receiver.moex.AbstractMoexReceiver.MOEX_ZONEDDATETIME_FORMATTER;
import steps.dev.exchange.gate.receiver.moex.interchange.ExchangeResponse;
import steps.dev.exchange.gate.receiver.moex.interchange.MarketdataDescription;
import steps.dev.myfinance7.common.model.exchange.ExchangeReceiverName;
import steps.dev.myfinance7.common.model.quote.SecurityQuote;

/**
 *
 * @author stepin
 */
public class BondsMoexReceiver extends AbstractMoexReceiver {

    private final InstrumentClient instrumentClient;

    public BondsMoexReceiver(
            ExchangeReceiverName receiverName,
            RestTemplate restTemplate,
            InstrumentClient instrumentClient,
            KafkaTemplate<String, SecurityQuote> kafkaTemplate,
            String kafkaTopic,
            AbstractMoexReceiverConfig parameters) {

        super(receiverName, restTemplate, kafkaTemplate, kafkaTopic, parameters);

        this.instrumentClient = instrumentClient;
    }

    @Override
    protected void update(ExchangeResponse response) {

        final MarketdataDescription marketdataDescription = retriveMetaData(response);
        response.setMarketdataDescription(marketdataDescription);

        debugPrintData(response);

//        List<SecurityQuote> quotes = Stream.of(response.getMarketdata().getData())
//                .map(marketdataRow -> mapToSecurityQuote(marketdataRow, marketdataDescription))
//                .collect(Collectors.toList());

        //instrumentClient
        //.updateQuotesByTickets(quotes);
        
        Stream.of(response.getMarketdata().getData())
                .map(marketdataRow -> mapToSecurityQuote(marketdataRow, marketdataDescription))
                .forEach(this::sendSecurityQuote);
        
    }

    private SecurityQuote mapToSecurityQuote(String[] marketdataRow, MarketdataDescription marketdataDescription) {

        SecurityQuote quote = new SecurityQuote();

        try {

            String ticket = marketdataRow[marketdataDescription.getSecidColumnNum()];

            String lastPriceAsString;

            if (!StringUtils.hasText(lastPriceAsString = marketdataRow[marketdataDescription.getLastColumnNum()])) {
                System.out.println("### last is null. returning ... ");
                return null;

            }

            double lastPrice = NumberFormat
                    .getInstance(Locale.forLanguageTag("ru_RU"))
                    .parse(lastPriceAsString)
                    .doubleValue();

            ZonedDateTime systime = MOEX_ZONEDDATETIME_FORMATTER.parse(
                    marketdataRow[marketdataDescription.getSystimeColumnNum()],
                    Locale.forLanguageTag("ru_RU"));
            System.out.println("### systime:" + systime);

            String datetime = MOEX_ZONEDDATETIME_FORMATTER.print(systime, Locale.forLanguageTag("ru_RU"));
            System.out.println("### datetime=" + datetime);

            quote.setTicket(ticket);
            quote.setPrice(lastPrice);
            quote.setDatetime(datetime);

        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        return quote;
    }

    private MarketdataDescription retriveMetaData(ExchangeResponse response) {

        final MarketdataDescription metaData = new MarketdataDescription();

        String[] columnNames = response.getMarketdata().getColumns();
        IntStream.range(0, columnNames.length)
                .forEach(i -> fillInIfMetaDataColumn(i, columnNames[i], metaData));

        return metaData;
    }

    protected void fillInIfMetaDataColumn(int i, String columnName, final MarketdataDescription metaData) {

        switch (columnName) {
            case "SECID":   //ticket
                metaData.setSecidColumnNum(i);
                break;

            case "LAST":     //last market day price
                metaData.setLastColumnNum(i);
                break;

            case "TIME":     //last price time
                metaData.setTimeColumnNum(i);
                break;

            case "SYSTIME":
                metaData.setSystimeColumnNum(i);
                break;

            case "UPDATETIME":
                metaData.setUpdatetimeColumnNum(i);
                break;
        }
    }

    private void debugPrintData(final ExchangeResponse response) {
        IntStream.range(0, response.getMarketdata().getData().length)
                .forEach(i -> debugPrintRow(i, response));
    }

    private void debugPrintRow(int rowNum, ExchangeResponse response) {

        System.out.println("*************************************************************************");
        int secidColNum = response.getMarketdataDescription().getSecidColumnNum();
        int lastColNum = response.getMarketdataDescription().getLastColumnNum();
        int timeColNum = response.getMarketdataDescription().getTimeColumnNum();
        int systimeColNum = response.getMarketdataDescription().getSystimeColumnNum();
        int updateColNum = response.getMarketdataDescription().getUpdatetimeColumnNum();

        System.out.println("### secidColNum=" + secidColNum + " : " + response.getMarketdata().getData()[rowNum][secidColNum]);
        System.out.println("### lastColNum=" + lastColNum + " : " + response.getMarketdata().getData()[rowNum][lastColNum]);
        System.out.println("### updateColNum=" + updateColNum + " : " + response.getMarketdata().getData()[rowNum][updateColNum]);
        System.out.println("### LOCALE:" + Locale.getDefault());
        System.out.println("### LOCALE_ru_RU:" + Locale.forLanguageTag("ru_RU"));

        if (response.getMarketdata().getData()[rowNum][lastColNum] == null) {
            System.out.println("### last is null. returning ... ");
            return;
        }

        try {

            ZonedDateTime systime = MOEX_ZONEDDATETIME_FORMATTER.parse(
                    response.getMarketdata().getData()[rowNum][systimeColNum],
                    Locale.forLanguageTag("ru_RU"));
            System.out.println("### systime:" + systime);

            LocalTime time = MOEX_TIME_FORMATTER.parse(
                    response.getMarketdata().getData()[rowNum][timeColNum],
                    Locale.forLanguageTag("ru_RU"));
            System.out.println("### time:" + time);

            double lastPrice = NumberFormat
                    .getInstance(Locale.forLanguageTag("ru_RU"))
                    .parse(response.getMarketdata().getData()[rowNum][lastColNum])
                    .doubleValue();
            System.out.println("### lastPrice:" + lastPrice);

        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        System.out.println("*************************************************************************");

    }

    @Override
    protected List<String> getTickets() {
        return instrumentClient
                .getTicketsByExchangeReceiverName(getReceiverName().name())
                .getBody();
    }

}
