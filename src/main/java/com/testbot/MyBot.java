package com.testbot;

import com.testbot.core.CoreBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class MyBot {

    public static void main(String[] args){
        TelegramBotsApi botsApi = null;
        try {
            //Инициализация бота - начало
            botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new CoreBot());//CoreBot это наш бот
            //Инициализация бота - конец

        } catch (TelegramApiException e) {
            throw new RuntimeException(e.getCause());
        }
    }


}
