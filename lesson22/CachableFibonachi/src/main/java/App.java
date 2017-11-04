
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

        System.out.println("First call");
        long startTime = System.nanoTime();
        System.out.println(calculator.fibonachi(10));
        System.out.println(calculator.fibonachi(40));
        System.out.println(calculator.fibonachi(46));
        System.out.println("Time (ns): " + (System.nanoTime() - startTime));

        cache.save(); //Сохраняем кэш в базу
    }
}
