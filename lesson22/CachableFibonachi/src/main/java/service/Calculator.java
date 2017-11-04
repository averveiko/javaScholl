package service;

public class Calculator implements ICalculator {

    @Override
    public int fibonachi(int n) {
        if (n < 1) throw new IllegalArgumentException("n must be > 0");
        if (n == 1 || n == 2) return 1;
        return fibonachi(n - 1) + fibonachi(n - 2);
    }
}
