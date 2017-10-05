package ru.sbt.averveyko;
/**
 * Метод execute принимает массив тасков,
 * это задания которые ExecutionManager должен выполнять параллельно
 * (в вашей реализации пусть будет в своем пуле потоков).
 * После завершения всех тасков должен выполниться callback (ровно 1 раз).
 *
 * Метод execute – это неблокирующий метод, который сразу возвращает объект Context.
 */
public interface ExecutionManager {
    Context execute(Runnable callback, Runnable... tasks);
}
