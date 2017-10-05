* Задание 2.
Ваша задача реализовать интерфейс ExecutionManager
```Java
public interface ExecutionManager {
    Context execute(Runnable callback, Runnable... tasks);
}
```
Метод execute принимает массив тасков, это задания которые ExecutionManager должен выполнять параллельно (в вашей реализации пусть будет в своем пуле потоков). После завершения всех тасков должен выполниться callback (ровно 1 раз). 

Метод execute – это неблокирующий метод, который сразу возвращает объект Context. 

Интерфейс Context:
* Метод getCompletedTaskCount() возвращает количество тасков, которые на текущий момент успешно выполнились.
* Метод getFailedTaskCount() возвращает количество тасков, при выполнении которых произошел Exception.
* Метод interrupt() отменяет выполнения тасков, которые еще не начали выполняться.
* Метод getInterruptedTaskCount() возвращает количество тасков, которые не были выполены из-за отмены (вызовом предыдущего метода).
* Метод isFinished() вернет true, если все таски были выполнены или отменены, false в противном случае.  

**Результат работы программы, выполнение 10 заданий в 5 потоков:**
_(видим что вызов callback произошел, когда тред, выполняющий последний task изменил свое состояние с RUNNABLE)_
<pre>
Thread-0 started..
Thread-1 started..
Thread-2 started..
Thread-3 started..
Thread-4 started..
context.isFinished(): false
context.getCompletedTaskCount(): 0
[START] Work id 0 in thread Thread-0
[START] Work id 1 in thread Thread-1
[START] Work id 2 in thread Thread-2
[START] Work id 3 in thread Thread-3
[START] Work id 4 in thread Thread-4
[FINISH] Work id 0 in thread Thread-0
[START] Work id 5 in thread Thread-0
[FINISH] Work id 1 in thread Thread-1
[START] Work id 6 in thread Thread-1
[FINISH] Work id 2 in thread Thread-2
[START] Work id 7 in thread Thread-2
[FINISH] Work id 3 in thread Thread-3
[START] Work id 8 in thread Thread-3
[FINISH] Work id 4 in thread Thread-4
[START] Work id 9 in thread Thread-4
[FINISH] Work id 5 in thread Thread-0
Thread-0 is waiting...
[FINISH] Work id 6 in thread Thread-1
Thread-1 is waiting...
[FINISH] Work id 7 in thread Thread-2
Thread-2 is waiting...
[FINISH] Work id 8 in thread Thread-3
Thread-3 is waiting...
[FINISH] Work id 9 in thread Thread-4

Callback run now!

Thread-4 is waiting...
context.isFinished(): true
context.getCompletedTaskCount(): 10
</pre>