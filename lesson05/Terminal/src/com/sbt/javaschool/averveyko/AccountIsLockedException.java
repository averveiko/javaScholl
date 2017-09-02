package com.sbt.javaschool.averveyko;

public class AccountIsLockedException extends IllegalStateException {
    public AccountIsLockedException(int sec) {
        super(String.valueOf(sec));
    }
}