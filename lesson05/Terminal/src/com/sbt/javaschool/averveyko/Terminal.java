package com.sbt.javaschool.averveyko;

public interface Terminal {
    void getMoney(String cardNumber, long amount);
    void putMoney(String cardNumber, long amount);
    long checkBalance(String cardNumber);
}
