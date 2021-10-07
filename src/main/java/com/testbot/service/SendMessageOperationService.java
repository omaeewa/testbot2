package com.testbot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.List;

import static com.testbot.constant.VarConstant.*;
import static java.lang.Math.toIntExact;
import static java.util.Arrays.asList;

public class SendMessageOperationService {
    private final String GREETING_MESSAGE = "Привет, приступим к планированию";
    private final String PLANNING_MESSAGE = "Вводите дела, после планирования нажмите кнопку закончить\nЗакончить планирование\n";
    private final String END_PLANNING_MESSAGE = "Планирование окончено, для просмотра нажмите кнопку показать дела";
    private final String INSTRUCTINOS = "Прочтете инструкцию?";

    private final ButtonService buttonService = new ButtonService();

    public SendMessage createGenerationInformation(Update update){
        SendMessage message = createSimpleMessage(update, GREETING_MESSAGE);//Наше сообщение
        ReplyKeyboardMarkup keyboardMarkup = buttonService.setButtons(buttonService.createButtons(//Создаем кнопки
                asList(START_PLANNING, END_PLANNING , SHOW_DEALS)));
        message.setReplyMarkup(keyboardMarkup);//Привязываем кнопки к сообщению
        return message;
    }

    public SendMessage createPlanningMessage(Update update){
        return createSimpleMessage(update, PLANNING_MESSAGE);//Наше сообщение


    }

    public SendMessage createEndPlanningMessage(Update update){
        return createSimpleMessage(update, END_PLANNING_MESSAGE);//Наше сообщение


    }

    public SendMessage createSimpleMessage(Update update, String message) {
        //Метод для создания сообщений
        SendMessage sendMessage = new SendMessage();//Хотим отпвавить сообщение
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));//Получаем айди чата от куда пришло сообщение
        sendMessage.setText(message);//Наш текст который мы отправляем
        return sendMessage;
    }

    public SendMessage createSimpleMessage(Update update, List<String> messages) {
        //Метод для создания сообщений
        SendMessage sendMessage = new SendMessage();//Хотим отпвавить сообщение
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));//Получаем айди чата от куда пришло сообщение
        StringBuilder message = new StringBuilder();
        for (String s : messages){
            message.append(s).append("\n");
        }
        sendMessage.setText(message.toString());//Наш текст который мы отправляем
        return sendMessage;
    }


    public SendMessage createInstructionMessage(Update update) {
        SendMessage sendMessage = createSimpleMessage(update, INSTRUCTINOS);
        InlineKeyboardMarkup replyKeyboardMarkup = buttonService.setInlineKeyMarkup(buttonService.createInlineButton(YES));
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return  sendMessage;
    }

    public EditMessageText createEditMessage(Update update, String instruction) {
        EditMessageText editMessageText = new EditMessageText();
        long mesId = update.getCallbackQuery().getMessage().getMessageId();
        long chatId = update.getCallbackQuery().getMessage().getChatId();
        editMessageText.setChatId(String.valueOf(chatId));
        editMessageText.setMessageId(toIntExact(mesId));
        editMessageText.setText(instruction);
        return editMessageText;
    }
}
