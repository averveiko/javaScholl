package ru.sbt.averveyko;

public interface Context {
    /**
     * возвращает количество тасков, которые на текущий момент успешно выполнились
     *
     * @return
     */
    int getCompletedTaskCount();

    /**
     * возвращает количество тасков, при выполнении которых произошел Exception
     *
     * @return
     */
    int getFailedTaskCount();

    /**
     * отменяет выполнения тасков, которые еще не начали выполняться
     */
    void interrupt();

    /**
     * возвращает количество тасков, которые не были выполены из-за отмены (вызовом предыдущего метода)
     *
     * @return
     */
    int getInterruptedTaskCount();

    /**
     * вернет true, если все таски были выполнены или отменены, false в противном случае
     *
     * @return
     */
    boolean isFinished();
}
