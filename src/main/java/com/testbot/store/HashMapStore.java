package com.testbot.store;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class HashMapStore implements BaseStore{
    private Map<LocalDate, List<String>> localStore = new HashMap<>();

    @Override
    public void save(LocalDate key, String deal) {
        if(localStore.containsKey(key)){//Если уже существует дела на эту дату
            ArrayList<String> alreadyExistDeals = new ArrayList<>( localStore.get(key));//Ищем наш массив
            alreadyExistDeals.add(deal);//Добавляем дело
            localStore.put(key, alreadyExistDeals);//Добавляем дело по дате
        }else{//Если по этой дате нет дел
            localStore.put(key, asList(deal));
        }
    }

    @Override
    public List<String> selectAll(LocalDate key) {
        return localStore.get(key);
    }
}
