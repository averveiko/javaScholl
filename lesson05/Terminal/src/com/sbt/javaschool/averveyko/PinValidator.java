package com.sbt.javaschool.averveyko;

import java.util.HashMap;
import java.util.Map;

public class PinValidator {
    //Хранилище значений № карты - пин
    Map<String, String> pinDB = new HashMap<>();

    //Ввел ли пользователь корректный ПИН-код
    boolean isAuthorized = false;

    public PinValidator() {
        pinDB.put("1111111111111111", "1111");
        pinDB.put("2222222222222222", "2222");
        pinDB.put("3333333333333333", "3333");
        pinDB.put("4444444444444444", "4444");
        pinDB.put("5555555555555555", "5555");
    }

    boolean validate (String cardNumber, String pin) {
        if (pin.length() != 4) return false;
        isAuthorized = pinDB.containsKey(cardNumber) && pinDB.get(cardNumber).equals(pin);
        return isAuthorized;
    }
}
