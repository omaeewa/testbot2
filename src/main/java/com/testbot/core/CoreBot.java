package com.testbot.core;

import com.testbot.service.SendMessageOperationService;
import com.testbot.store.HashMapStore;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;

import static com.testbot.constant.VarConstant.*;

public class CoreBot extends TelegramLongPollingBot {
    private SendMessageOperationService sendMessageOperationService = new SendMessageOperationService();
    private HashMapStore store = new HashMapStore();
    private boolean startPlanning;


    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){//Проверяем было ли сообщение, и содержит ли он текст
            switch (update.getMessage().getText()){//Смотрим какое сообщение
                case START://(константа) сообщение /start
                    executeMessage(sendMessageOperationService.createGenerationInformation(update));//Используем метод нашего класса
                    executeMessage(sendMessageOperationService.createInstructionMessage(update));
                    break;
                case START_PLANNING:
                    startPlanning = true;
                    executeMessage(sendMessageOperationService.createPlanningMessage(update));
                    break;
                case END_PLANNING:
                    startPlanning = false;
                    executeMessage(sendMessageOperationService.createEndPlanningMessage(update));
                    break;
                case SHOW_DEALS:
                    if(!startPlanning){
                        executeMessage(sendMessageOperationService.createSimpleMessage(update, store.selectAll(LocalDate.now())));//Передаем текущую дату
                    }

                default:
                    if(startPlanning){
                        store.save(LocalDate.now(), update.getMessage().getText());
                    }
            }
            if (update.hasCallbackQuery()){//Если нажал на кнопку
                String instruction = "бот для формирования дел на день";
                String callDate = update.getCallbackQuery().getData();
                switch (callDate.toLowerCase()){
                    case YES:
                        EditMessageText text = sendMessageOperationService.createEditMessage(update, instruction);
                        executeMessage(text);
                }
            }

        }

    }
    @Override
    public String getBotUsername() {
        return "test_studyyy_bot";
    }

    @Override
    public String getBotToken() {
        return "2068658613:AAGFxHSnob5HgBOK3qhHuaKeBj5JXww_NMM";
    }

    private <T extends BotApiMethod> void executeMessage(T sendMessage){
        //Метод для выполнения отправки сообщения
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
