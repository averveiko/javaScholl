package com.sbt.javaschool.averveyko;

public class TerminalImpl implements Terminal {
    private final TerminalServer server;
    private final PinValidator pinValidator;

    private LockHelper lockHelper = new LockHelper();

    private int pinErrorCount = 0;

    public TerminalImpl(TerminalServer server, PinValidator pinValidator) {
        this.server = server;
        this.pinValidator = pinValidator;
    }

    public boolean checkPin(String cardNumber, String pin) {

        if (lockHelper.isLocked())
            throw new AccountIsLockedException((int)(Math.ceil(lockHelper.getCoutdown() / 1000.0)));

        if (pinValidator.validate(cardNumber,pin)){
            pinErrorCount = 0;
            return true;
        }

        pinErrorCount += 1;
        if (pinErrorCount == 3) {
            lockHelper.lock();
            pinErrorCount = 0;
        }

        return false;
    }

    @Override
    public void getMoney(String cardNumber, long amount) {
        if (lockHelper.isLocked())
            throw new AccountIsLockedException((int)(Math.ceil(lockHelper.getCoutdown() / 1000.0)));
        if (amount % 100 != 0)
            throw new IllegalArgumentException("amount % 100 != 0");

        server.getMoney(cardNumber, amount);
    }

    @Override
    public void putMoney(String cardNumber, long amount) {
        if (lockHelper.isLocked())
            throw new AccountIsLockedException((int)(Math.ceil(lockHelper.getCoutdown() / 1000.0)));
        if (amount % 100 != 0)
            throw new IllegalArgumentException("amount % 100 != 0");

        server.putMoney(cardNumber, amount);
    }

    @Override
    public long checkBalance(String cardNumber) {
        if (lockHelper.isLocked())
            throw new AccountIsLockedException((int)(Math.ceil(lockHelper.getCoutdown() / 1000.0)));

        return server.checkBalance(cardNumber);
    }

    /**
     * Вспомогательный класс, отслеживающий время блокировки аккаунта
     */
    private static class LockHelper {
        private static final long lockInterval = 5000; //Интервал блокировки - 5 сек
        long lockTime = -1;

        boolean isLocked () {
            if (lockTime == -1) return false; //Не был блокирован
            if (lockTime + lockInterval > System.currentTimeMillis()){
                return true; //Интервал блокировки еще не истек
            }
            //Интервал блокировки истек
            lockTime = -1;
            return false;
        }
        void lock() {
            if (lockTime != -1) return; //Уже заблокирован
            lockTime = System.currentTimeMillis();
        }
        //Возвращает время в милисекундах до разблокировки
        long getCoutdown() {
            if (lockTime != -1)
                return lockTime + lockInterval - System.currentTimeMillis();
            return 0;
        }
    }
}

