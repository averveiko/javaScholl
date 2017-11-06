
import dao.h2.AbstractH2CacheDao;
import dao.h2.H2CacheDao;
import model.Cache;
import model.ICache;
import service.CacheProxy;
import service.Calculator;
import service.ICalculator;

public class App {
    public static void main(String[] args) {

        ICache cache = new Cache(new H2CacheDao(AbstractH2CacheDao.H2_URL)); // Создаем кэш
        cache.load(); // Загружаем кэш из базы

        ICalculator calculator = CacheProxy.cache(new Calculator(), cache);

        long start = System.nanoTime();

        System.out.println("fib 10: " + calculator.fibonachi(10));
        System.out.println("fib 15: " + calculator.fibonachi(15));
        System.out.println("fib 20: " + calculator.fibonachi(20));
        System.out.println("fib 25: " + calculator.fibonachi(25));
        System.out.println("fib 30: " + calculator.fibonachi(30));
        System.out.println("fib 35: " + calculator.fibonachi(35));
        System.out.println("fib 40: " + calculator.fibonachi(40));
        System.out.println("fib 45: " + calculator.fibonachi(45));
        System.out.println("fib 46: " + calculator.fibonachi(46));

        System.out.println("\nExecution time (ns) " + (System.nanoTime() - start));

    }
}
