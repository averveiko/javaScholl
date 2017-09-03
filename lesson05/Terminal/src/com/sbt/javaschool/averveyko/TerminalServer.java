package com.sbt.javaschool.averveyko;

import java.util.HashMap;
import java.util.Map;

public class TerminalServer implements Terminal {
    //База данных карт: номер карты - баланс
    Map<String, Long> cardDB = new HashMap<>();

    public TerminalServer() {
        //Заполняем базу данных карт
        cardDB.put("1111111111111111", 100000L);
        cardDB.put("2222222222222222", 200000L);
        cardDB.put("3333333333333333", 300000L);
        cardDB.put("4444444444444444", 400000L);
        cardDB.put("5555555555555555", 500000L);
    }

    @Override
    public long checkBalance (String cardNumber) {
            return cardDB.get(cardNumber);
    }

    @Override
    public void getMoney(String cardNumber, long amount) {
        long balance = cardDB.get(cardNumber);
        long newBalance = balance - amount;
        if (newBalance < 0) throw new InsufficientFundsExceptions("insufficient funds on the account");
        cardDB.put(cardNumber, newBalance);
    }

    @Override
    public void putMoney(String cardNumber, long amount) {
        long balance = cardDB.get(cardNumber);
        long newBalance = balance + amount;
        cardDB.put(cardNumber, newBalance);
    }
}
