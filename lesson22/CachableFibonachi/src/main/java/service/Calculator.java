package service;

public class Calculator implements ICalculator {
    /**
     * Вычисляет числа Фибоначчи.
     * Максимальное n = 46, т.к. 47-й элемент последовательности фибоначчи = 2 971 215 073,
     * что больше Integer.MAX_VALUE (по условиям задания оперируем int)
     *
     * @param n Порядковый номер числа фибонначи (1 <= n <= 46)
     * @return число Фибонначи
     */
    @Override
    public int fibonachi(int n) {
        int a = 1;
        int b = 1;
        int res = 0;

        if (n < 1 || n > 46) throw new IllegalArgumentException("1 <= n <= 46");
        if (n == 1 || n == 2) return 1;

        for (int i = 2; i < n; i++) {
            res = a + b;
            a = b;
            b = res;
        }
        return res;
    }
}
