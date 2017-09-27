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

**FixedThreadPool - результат обработки 8 заданий в 4 потока:**
<pre>
Thread-0 started..
Thread-1 started..
Thread-2 started..
Thread-3 started..
[START] Work id 0 in thread Thread-0
[START] Work id 1 in thread Thread-1
[START] Work id 2 in thread Thread-2
[START] Work id 3 in thread Thread-3
[FINISH] Work id 0 in thread Thread-0
[START] Work id 4 in thread Thread-0
[FINISH] Work id 1 in thread Thread-1
[START] Work id 5 in thread Thread-1
[FINISH] Work id 2 in thread Thread-2
[START] Work id 6 in thread Thread-2
[FINISH] Work id 3 in thread Thread-3
[START] Work id 7 in thread Thread-3
[FINISH] Work id 4 in thread Thread-0
Thread-0 is waiting...
[FINISH] Work id 5 in thread Thread-1
Thread-1 is waiting...
[FINISH] Work id 6 in thread Thread-2
Thread-2 is waiting...
[FINISH] Work id 7 in thread Thread-3
Thread-3 is waiting...
</pre>
**Добавлены еще 2 задания в очередь:**
<pre>
[START] Work id 8 in thread Thread-0
[START] Work id 9 in thread Thread-1
[FINISH] Work id 8 in thread Thread-0
Thread-0 is waiting...
[FINISH] Work id 9 in thread Thread-1
Thread-1 is waiting...
</pre>
<hr />

**ScalableThreadPool - результат обработки 20 заданий в 4-6 потоков:**
<pre>
add new thread Thread-0
add new thread Thread-1
add new thread Thread-2
add new thread Thread-3
add extend thread Thread-4
add extend thread Thread-5
[START] Work id 0 in thread Thread-0
[START] Work id 1 in thread Thread-1
[START] Work id 2 in thread Thread-2
[START] Work id 3 in thread Thread-3
[START] Work id 4 in thread Thread-4
[START] Work id 5 in thread Thread-5
[FINISH] Work id 0 in thread Thread-0
[START] Work id 6 in thread Thread-0
[FINISH] Work id 1 in thread Thread-1
[START] Work id 7 in thread Thread-1
[FINISH] Work id 2 in thread Thread-2
[START] Work id 8 in thread Thread-2
[FINISH] Work id 3 in thread Thread-3
[START] Work id 9 in thread Thread-3
[FINISH] Work id 4 in thread Thread-4
remove extend thread Thread-5
[FINISH] Work id 5 in thread Thread-5
[FINISH] Work id 7 in thread Thread-1
remove extend thread Thread-4
[FINISH] Work id 6 in thread Thread-0
[FINISH] Work id 8 in thread Thread-2
[FINISH] Work id 9 in thread Thread-3
Add another 10 tasks
[START] Work id 10 in thread Thread-1
[START] Work id 13 in thread Thread-0
[START] Work id 12 in thread Thread-2
[START] Work id 11 in thread Thread-3
add extend thread Thread-6
add extend thread Thread-7
[START] Work id 14 in thread Thread-6
[START] Work id 15 in thread Thread-7
[FINISH] Work id 10 in thread Thread-1
[START] Work id 16 in thread Thread-1
[FINISH] Work id 13 in thread Thread-0
[START] Work id 17 in thread Thread-0
[FINISH] Work id 12 in thread Thread-2
[START] Work id 18 in thread Thread-2
[FINISH] Work id 11 in thread Thread-3
[START] Work id 19 in thread Thread-3
[FINISH] Work id 14 in thread Thread-6
remove extend thread Thread-7
[FINISH] Work id 15 in thread Thread-7
[FINISH] Work id 16 in thread Thread-1
remove extend thread Thread-6
[FINISH] Work id 17 in thread Thread-0
[FINISH] Work id 19 in thread Thread-3
[FINISH] Work id 18 in thread Thread-2
</pre>