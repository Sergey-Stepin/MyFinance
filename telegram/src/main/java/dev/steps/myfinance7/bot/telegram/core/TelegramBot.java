/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.steps.myfinance7.bot.telegram.core;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 *
 * @author stepin
 */

//@Component
public class TelegramBot extends TelegramLongPollingBot {
    
    public TelegramBot(){
        super();
        
        System.out.println("### Constact ProbBot");
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("### received:" + update);
        
        Message message = update.getMessage();
	String msgText = message.getText();
        
        System.out.println("### text:" + msgText);
        System.out.println("### from:" + message.getFrom());
        System.out.println("### message.getCaption():" + message.getCaption());
        System.out.println("### message.getConnectedWebsite():" + message.getConnectedWebsite());
        System.out.println("### message.getChatId():" + message.getChatId());
        System.out.println("### message.getChat():" + message.getChat());
        System.out.println("### CHAT.getDescription():" + message.getChat().getDescription());
        System.out.println("### CHAT.getTitle():" + message.getChat().getTitle());
        System.out.println("### CHAT.getUserName():" + message.getChat().getUserName());
        System.out.println("### is channel:" + message.getChannelChatCreated());
        
	sendMsg(update.getMessage().getChatId().toString(), msgText);
    }

    public synchronized void sendMsg(String chatId, String s) {
        System.out.println("### chatId=" + chatId);
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            System.out.println("try to send: " + sendMessage.getText());
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "stepsProb_A_bot";
    }

    @Override
    public String getBotToken() {
        return "1137051679:AAEi0tcQ_u4T13K6uMYuVKNjVKm1sEUcwLY";
    }
}