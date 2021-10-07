package com.testbot.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class ButtonService {
    public ReplyKeyboardMarkup setButtons(List<KeyboardRow> keyboardRowList){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();//Инициализируем кнопки
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);//устанавливаем размер
        replyKeyboardMarkup.setKeyboard(keyboardRowList);//Устанавливаем свойства
        return  replyKeyboardMarkup;
    }
    public List<KeyboardRow> createButtons(List<String> buttonsName){//Принимаем названия кнопок
        List<KeyboardRow> keyboardRows = new ArrayList<>();//Для создания нескольких полосок с кнопками
        KeyboardRow keyboardRow = new KeyboardRow();//Одна полоска с кнопками
        for (String buttonName: buttonsName){
            keyboardRow.add(new KeyboardButton(buttonName));
        }
        keyboardRows.add(keyboardRow);//Добавляем нашу полоску
        return keyboardRows;

    }


    public List<List<InlineKeyboardButton>> createInlineButton(String buttonName){
        List<List<InlineKeyboardButton>> inlineKeyButtonList = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardButtonsRow = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText(buttonName);
        inlineKeyboardButton.setCallbackData(buttonName);
        inlineKeyboardButtonsRow.add(inlineKeyboardButton);
        inlineKeyButtonList.add(inlineKeyboardButtonsRow);
        return inlineKeyButtonList;
    }

    public InlineKeyboardMarkup setInlineKeyMarkup(List<List<InlineKeyboardButton>> inlineList){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(inlineList);
        return inlineKeyboardMarkup;
    }

}
