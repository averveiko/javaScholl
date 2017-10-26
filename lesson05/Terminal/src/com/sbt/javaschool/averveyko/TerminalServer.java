package com.sbt.javaschool.averveyko;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TerminalServer implements Terminal {
    //База данных карт: номер карты - баланс
    private Map<String, Long> cardDB = new HashMap<>();

    public TerminalServer() {
        //Заполняем базу данных карт
        cardDB.put("1111111111111111", 100000L);
        cardDB.put("2222222222222222", 200000L);
        cardDB.put("3333333333333333", 300000L);
        cardDB.put("4444444444444444", 400000L);
        cardDB.put("5555555555555555", 500000L);
    }

    @Override
    public long checkBalance (String cardNumber) throws IOException {
        ConnectionErrorSimulator();
        return cardDB.get(cardNumber);
    }

    @Override
    public void getMoney(String cardNumber, long amount) throws IOException {
        ConnectionErrorSimulator();
        long balance = cardDB.get(cardNumber);
        long newBalance = balance - amount;
        if (newBalance < 0) throw new InsufficientFundsExceptions("Недостаточно средств на счете");
        cardDB.put(cardNumber, newBalance);
    }

    @Override
    public void putMoney(String cardNumber, long amount) throws IOException {
        ConnectionErrorSimulator();
        long balance = cardDB.get(cardNumber);
        long newBalance = balance + amount;
        cardDB.put(cardNumber, newBalance);
    }

    /**
     * Метод симулирует проблемы со связью
     * С вероятностью 30% бросает IOExeption
     */
    private void ConnectionErrorSimulator() throws IOException {
        final Random rnd = new Random();
        if (rnd.nextInt(3) == 2) throw new IOException("Возникли проблемы с сетью.");
    }
}
