
import service.CacheProxy;
import service.Calculator;
import service.ICalculator;

public class App {
    public static void main(String[] args) {

        ICalculator calculator = CacheProxy.cache(new Calculator());

        System.out.println("First call");
        long startTime = System.nanoTime();
        System.out.println(calculator.fibonachi(46));
        System.out.println("Time (ns): " + (System.nanoTime() - startTime));

        System.out.println("Second call");
        startTime = System.nanoTime();
        System.out.println(calculator.fibonachi(46));
        System.out.println("Time (ns): " + (System.nanoTime() - startTime));
    }
}
