package service;

public class Calculator implements ICalculator {
    @Override
    public int fibonachi(int n) {
        int a = 1;
        int b = 1;
        int res = 0;

        if (n < 1) throw new IllegalArgumentException("n must be > 0");
        if (n == 1 || n == 2) return 1;

        for (int i = 2; i < n; i++) {
            res = a + b;
            a = b;
            b = res;
        }
        return res;
    }

}
