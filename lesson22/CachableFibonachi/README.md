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

#### Первый запуск программы (вычисляем значения)
<pre>
calculate value fib 10: 55
calculate value fib 15: 610
calculate value fib 20: 6765
calculate value fib 25: 75025
calculate value fib 30: 832040
calculate value fib 35: 9227465
calculate value fib 40: 102334155
calculate value fib 45: 1134903170
calculate value fib 46: 1836311903

Execution time (ns) 317140757
</pre>

#### Второй запуск программы (достаем значения из кэша)
<pre>
get value from cache fib 10: 55
get value from cache fib 15: 610
get value from cache fib 20: 6765
get value from cache fib 25: 75025
get value from cache fib 30: 832040
get value from cache fib 35: 9227465
get value from cache fib 40: 102334155
get value from cache fib 45: 1134903170
get value from cache fib 46: 1836311903

Execution time (ns) 43965934
</pre>

(Disclaimer: замер примерный, без прогрева и прочего)</p>
**Время выполнения**
<code>
<table>
<tr>
  <td>317140757</td><td>запуск с пустым кэшем (ns)</td>
</tr>
<tr>
  <td>43965934</td><td>запуск с уже заполненным кэшем (ns)</td>
</tr>
</table>
</code>