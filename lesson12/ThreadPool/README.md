### Реализовать ThreadPool

```Java
public interface ThreadPool {
    /**
     * Запускает потоки. Потоки бездействуют, до тех пор пока
     * не появится новое задание в очереди (см. execute).
     */
    void start();

    /**
     * Складывает это задание в очередь. Освободившийся поток должен выполнить это задание.
     * Каждое задание должны быть выполнено ровно 1 раз.
     * @param runnable Задание.
     */
    void execute(Runnable runnable);
```

Сделать 2 реализации ThreadPool:
1) FixedThreadPool - Количество потоков задается в конструкторе и не меняется.
2) ScalableThreadPool в конструкторе задается минимальное и максимальное(int min, int max) число потоков,
количество запущенных потоков может быть увеличено от минимального к максимальному, если при добавлении нового задания в очередь нет свободного потока для исполнения этого задания. При отсутствии задания в очереди, количество потоков опять должно быть уменьшено до значения min

**FixedThreadPool - результат обработки 10 заданий в 3 потока:**
<pre>
[START] Work id 0 in thread Thread-0
[START] Work id 1 in thread Thread-1
[FINISH] Work id 0 in thread Thread-0
[START] Work id 2 in thread Thread-2
[FINISH] Work id 1 in thread Thread-1
[FINISH] Work id 2 in thread Thread-2
[START] Work id 3 in thread Thread-0
[START] Work id 4 in thread Thread-1
[FINISH] Work id 3 in thread Thread-0
[START] Work id 5 in thread Thread-2
[FINISH] Work id 4 in thread Thread-1
[START] Work id 6 in thread Thread-0
[FINISH] Work id 5 in thread Thread-2
[START] Work id 7 in thread Thread-1
[FINISH] Work id 6 in thread Thread-0
[FINISH] Work id 7 in thread Thread-1
[START] Work id 8 in thread Thread-2
[START] Work id 9 in thread Thread-0
[FINISH] Work id 8 in thread Thread-2
[FINISH] Work id 9 in thread Thread-0
</pre>