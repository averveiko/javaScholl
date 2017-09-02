package com.sbt.javaschool.averveyko;

import java.util.Scanner;
public class TerminalUI {

    String cardNumber;

    private TerminalImpl terminal = new TerminalImpl(new TerminalServer(), new PinValidator());

    Scanner in = new Scanner(System.in);

    void start() {
        while (cardMenu()) {
            operationMenu();
        }
        System.out.println("Возьмите вашу карту");
    }

    private boolean cardMenu() {
        System.out.print("Вставьте вашу карту (введите номер или q для завершения работы): ");
        cardNumber = in.nextLine().replaceAll("\\s+", "");

        if (cardNumber.equalsIgnoreCase("q")) return false;

        while (true) {
            System.out.print("Введите ПИН-код (или 'q' для завершения работы): ");
            String pin = in.nextLine().replaceAll("\\s+", "");

            if (pin.equalsIgnoreCase("q")) return false;


            try {
                if (!terminal.checkPin(cardNumber, pin)) {
                    System.out.println("Неверно введен ПИН-код карты");
                    continue;
                }
            } catch (AccountIsLockedException e) {
                System.out.println("Аккаунт заблокирован. " + e.getMessage());
                continue;
            }
            return true;
        }
    }

    private void operationMenu() {
        while (true) {
            System.out.println("\nВыбор операции:\n" +
                    "1. Проверить баланс карты\n" +
                    "2. Снять наличные\n" +
                    "3. Внести наличные\n" +
                    "4. Завершить работу\n" +
                    "Введите номер требуемой операции [1-4]: ");
            int operation = in.nextInt();
            switch (operation) {
                case 1:

                    try {
                        System.out.println("\nБаланс карты: " + terminal.checkBalance(cardNumber));
                    } catch (AccountIsLockedException e) {
                        System.out.println("\nАккаунт заблокирован. " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("\nСнятие наличных:");
                    break;
                case 3:
                    System.out.println("\nВнесение наличных:");
                    break;
                case 4:
                    return;
            }
        }
    }
}
