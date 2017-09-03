package com.sbt.javaschool.averveyko;

import java.io.IOException;
import java.util.Scanner;

public class TerminalUI {

    private TerminalImpl terminal = new TerminalImpl(new TerminalServer(), new PinValidator());

    private Scanner in = new Scanner(System.in);

    public void start() {

        while (true){
            String cardNumber = cardMenu();
            if (! cardNumber.isEmpty())
                operationMenu(cardNumber);
            break;
        }
        System.out.println("\nЗаберите вашу карту");
    }

    private String cardMenu() {
        System.out.print("\nВставьте вашу карту (введите номер или q для завершения работы): ");
        String cardNumber = in.nextLine().replaceAll("\\s+", "");

        if (cardNumber.equalsIgnoreCase("q")) return "";

        while (true) {
            System.out.print("Введите ПИН-код (или 'q' для завершения работы): ");
            String pin = in.nextLine().replaceAll("\\s+", "");

            if (pin.equalsIgnoreCase("q")) return "";

            try {
                if (!terminal.checkPin(cardNumber, pin)) {
                    System.out.println("Неверно введен ПИН-код карты");
                    continue;
                }
            } catch (AccountIsLockedException e) {
                System.out.println("Аккаунт заблокирован. До разблокировки осталось секунд: " + e.getMessage());
                continue;
            }
            return cardNumber;
        }
    }

    private void operationMenu(String cardNumber) {
        while (true) {
            System.out.print("\nВыбор операции:\n" +
                    "1. Проверить баланс карты\n" +
                    "2. Снять наличные\n" +
                    "3. Внести наличные\n" +
                    "4. Завершить работу\n" +
                    "Введите номер требуемой операции [1-4]: ");

            int operation = in.nextInt();

            try {
                if (operation == 1) getBalance(cardNumber);
                if (operation == 2) getMoney(cardNumber);
                if (operation == 3) putMoney(cardNumber);
                if (operation == 4) return;

            } catch (SecurityException e) {
                System.out.println("Для начала работы необходимо ввести ПИН-код.");
            } catch (AccountIsLockedException e) {
                System.out.println("Аккаунт заблокирован. До разблокировки осталось секунд: " + e.getMessage());
            } catch (InsufficientFundsExceptions e) {
                System.out.println("Недостаточно средств на счете. Операция не выполнена.");
            } catch (IllegalArgumentException e) {
                System.out.println("Сумма должна быть кратна 100. Операция не выполнена.");
            } catch (IOException e) {
                System.out.println("Возникли проблемы со связью, попробуйте повторить операцию.");
            }
        }
    }

    private void getBalance(String cardNumber) throws IOException  {
        System.out.println("\nБаланс карты: " + terminal.checkBalance(cardNumber));
    }

    private void getMoney(String cardNumber) throws IOException {
        System.out.println("\nСнятие наличных");
        System.out.println("Введите сумму кратную 100: ");
        int amount = in.nextInt();
        terminal.getMoney(cardNumber, amount);
        System.out.println("Операция выполнена успешно, заберите деньги.");
    }

    private void putMoney(String cardNumber) throws IOException {
        System.out.println("\nВнесение наличных");
        System.out.println("Введите сумму кратную 100: ");
        int amount = in.nextInt();
        terminal.putMoney(cardNumber, amount);
        System.out.println("Операция выполнена успешно, деньги зачислены.");
    }
}


