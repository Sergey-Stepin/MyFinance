/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.steps.myfinance7.bot.telegram.service;

import dev.steps.myfinance7.bot.telegram.core.TelegramBot;
import java.time.ZonedDateTime;
import static java.time.format.DateTimeFormatter.ISO_ZONED_DATE_TIME;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import steps.dev.myfinance7.common.model.quote.SecurityQuote;

/**
 *
 * @author stepin
 */
@Service
public class QuotesUpdateService {
    
    private TelegramBot bot;
    
    public QuotesUpdateService(){
        System.out.println("### TelegramBotsApi initializing ... ");

        //bot = new ProbBot();
        
        ApiContextInitializer.init();
        System.out.println("### INITED");
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        System.out.println("### API created" + telegramBotsApi);
        
        try {
            telegramBotsApi.registerBot(bot = new TelegramBot());
            System.out.println("### TelegramBotsApi ProbBot has been created ");
            
        } catch (TelegramApiRequestException e) {
            System.out.println("ERROR: " + e.toString());
            e.printStackTrace();
        }
        
    }

    @KafkaListener (
            topics = {
                "${kafka.currency-rates.topic}", "${kafka.security-quotes.topic}"} , 
            groupId = "${kafka.consumer.group}")
    public void update(SecurityQuote quote) {
        System.out.println("%%% ticket=" + quote.getTicket());
        System.out.println("%%% price=" + quote.getPrice());
        System.out.println("%%% dateTime=" + quote.getDatetime());
        
        ZonedDateTime dateTime = ZonedDateTime.parse(quote.getDatetime(), ISO_ZONED_DATE_TIME);
        System.out.println("%%% parsed=" + dateTime);
        Date rateDate = Date.from(dateTime.toInstant());
        
        String messageToSend = rateDate + " : " + quote.getTicket() + " = " + quote.getPrice();
        
        System.out.println("### try sending ...");
        bot.sendMsg("-463629882", messageToSend);
    }
}
