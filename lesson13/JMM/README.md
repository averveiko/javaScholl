* [Задание 1.](https://github.com/averveiko/javaSchool/tree/master/lesson13/JMM/src/main/java/ru/sbt/averveyko/Task)
Ваша задача реализовать класс Task имеющий один метод get():
```Java
public class Task<T> {
    …
 
    public Task(Callable<? extends T> callable) {
        //...
     }
 
    public T get() {
         … // todo implement me
    }
}
```
Ваша задача реализовать метод get() который возвращает результат работы Callable. Выполнение callable должен начинать тот поток, который первый вызвал метод get(). Если несколько потоков одновременно вызывают этот метод, то выполнение должно начаться только в одном потоке, а остальные должны ожидать конца выполнения (не нагружая процессор). 

**Результат работы программы:**
_1й вызов - вычисление, последующие - кэшированное значение_
<pre>
calculate result
return cashed value
return cashed value
return cashed value
return cashed value
return cashed value
return cashed value
return cashed value
return cashed value
return cashed value
</pre>
_1й вызов  - попытка вычисления, последующие - кэшированный exception_
<pre>
calculate result
ComputeException: java.lang.ArithmeticException: / by zero
ComputeException: java.lang.ArithmeticException: / by zero
ComputeException: java.lang.ArithmeticException: / by zero
ComputeException: java.lang.ArithmeticException: / by zero
ComputeException: java.lang.ArithmeticException: / by zero
ComputeException: java.lang.ArithmeticException: / by zero
ComputeException: java.lang.ArithmeticException: / by zero
ComputeException: java.lang.ArithmeticException: / by zero
ComputeException: java.lang.ArithmeticException: / by zero
ComputeException: java.lang.ArithmeticException: / by zero
</pre>

* [Задание 2 см. тут](https://github.com/averveiko/javaSchool/tree/master/lesson14/ExecutionManager)
