package ru.sbt.averveyko;

public interface ThreadCompleteListener {
    void notifyOfThreadComplete(final Thread thread);
    void notifyOfThreadFailed(final Thread thread);
}
