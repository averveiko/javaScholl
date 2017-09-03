package com.sbt.javaschool.averveyko;

import java.io.IOException;

public interface Terminal {
    void getMoney(String cardNumber, long amount) throws IOException;
    void putMoney(String cardNumber, long amount) throws IOException;
    long checkBalance(String cardNumber) throws IOException;
}
