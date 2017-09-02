package com.sbt.javaschool.averveyko;

import java.util.HashMap;
import java.util.Map;

public class PinValidator {
    //Хранилище значений № карты - пин
    Map<String, String> pinDB = new HashMap<>();

    public PinValidator() {
        pinDB.put("1111111111111111", "1111");
        pinDB.put("2222222222222222", "2222");
        pinDB.put("2222222222222222", "2222");
        pinDB.put("2222222222222222", "2222");
        pinDB.put("2222222222222222", "2222");
    }

    boolean validate (String cardNumber, String pin) {
        if (pin.length() != 4) return false;
        return pinDB.containsKey(cardNumber) && pinDB.get(cardNumber).equals(pin);
    }
}
