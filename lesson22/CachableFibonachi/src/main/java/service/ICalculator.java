package service;

public interface ICalculator {
    @Cachable(persistent = true)
    int fibonachi(int n);
}
