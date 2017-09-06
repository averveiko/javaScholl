package com.sbt.javaschool.averveyko.ProxyCalculator;

public class Calculator implements ICalculator {
    /**
     * Вычисляет значение выражения вида x + y
     * Разделение пробелами обязательно
     * Поддерживаемые операции: + - / *
     * Возвращает результат выражения
     */
    @Override
    public double eval(String expression) {

        String[] expr = expression.split(" ");

        if (expr.length != 3) throw new IllegalArgumentException("Неккоректное выражение, требуется строка вида 2.5 + 5.2");

        //Левый операнд
        double opLeft = Double.parseDouble(expr[0]);
        //Правый операнд
        double opRight = Double.parseDouble(expr[2]);
        //Оператор
        String operator = expr[1];

        double result = 0;

        switch (operator) {
            case "+":
                result = opLeft + opRight;
                break;
            case "-":
                result = opLeft - opRight;
                break;
            case "/":
                result = opLeft / opRight;
                break;
            case "*":
                result = opLeft * opRight;
                break;
            default:
                throw new IllegalArgumentException("Неккоректный оператор, поддерживаются + - / *");
        }

        return result;
    }
}
