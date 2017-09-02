package com.sbt.javaschool.averveyko;

public interface Terminal {
    boolean getMoney(String cardNumber, long amount);
    boolean putMoney(String cardNumber, long amount);
    long checkBalance(String cardNumber);
}
