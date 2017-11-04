### Разработать продвинутый кэш, который помнит о кэшированных данных после перезапуска приложения

```Java
public @interface Cachable {
    boolean persistent() default false;
}

public interface ICalculator {
    @Cachable(persistent = true)
    int fibonachi(int n);
}
```

#### Benchmark