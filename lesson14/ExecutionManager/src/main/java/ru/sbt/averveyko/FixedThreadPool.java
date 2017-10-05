package ru.sbt.averveyko;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class FixedThreadPool implements ThreadCompleteListener, Context {
    private enum NOTIFY_EVENT {THREAD_COMPLETE, THREAD_FAILED;}
    private volatile int taskCompleteCount = 0;
    private volatile int taskFailedCount = 0;
    private volatile int taskInterruptedCount = 0;

    private final int threadCount;
    private final NotifyingPoolWorker[] threads;
    private final Queue<Runnable> queue;
    private Runnable callback = null;

    public FixedThreadPool(int threadCount) {
        this.threadCount = threadCount;
        queue = new LinkedList<>();
        threads = new NotifyingPoolWorker[threadCount];
    }

    //@Override
    public void start(Runnable callback) {
        this.callback = callback;
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new NotifyingPoolWorker();
            threads[i].addListener(this);
            threads[i].start();
            System.err.println(threads[i].getName() + " started..");
        }
    }

   // @Override
    public void execute(Runnable runnable) {
        synchronized (queue) {
            queue.add(runnable);
            queue.notify();
        }
    }

    @Override
    public void notifyOfThreadComplete(Thread thread) {
        taskCompleteCount++;
        //check for all tasks complete
        if (isFinished()) callback.run();
    }

    @Override
    public void notifyOfThreadFailed(Thread thread) {
        taskFailedCount++;
    }

    private class NotifyingPoolWorker extends Thread {

        private final Set<ThreadCompleteListener> listeners = new CopyOnWriteArraySet<ThreadCompleteListener>();

        public final void addListener(final ThreadCompleteListener listener) {
            listeners.add(listener);
        }
        public final void removeListener(final ThreadCompleteListener listener) {
            listeners.remove(listener);
        }
        private final void notifyListeners(NOTIFY_EVENT event) {
            for (ThreadCompleteListener listener : listeners) {
                if (event == NOTIFY_EVENT.THREAD_COMPLETE)
                    listener.notifyOfThreadComplete(this);
                else
                    listener.notifyOfThreadFailed(this);
            }
        }

        @Override
        public void run() {
            Runnable r;

            while(true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            System.err.println(this.getName() + " is waiting...");
                            queue.wait();
                        } catch (InterruptedException e) {
                        }
                    }
                    r = queue.remove();
                }
                try {
                    r.run();
                } catch (Exception e) {
                    notifyListeners(NOTIFY_EVENT.THREAD_FAILED);
                } finally {
                    notifyListeners(NOTIFY_EVENT.THREAD_COMPLETE);
                }
            }
        }
    }

    //Context methods
    @Override
    public int getCompletedTaskCount() {
        return taskCompleteCount;
    }

    @Override
    public int getFailedTaskCount() {
        return taskFailedCount;
    }

    @Override
    public void interrupt() {
        synchronized (queue) {
            if (!queue.isEmpty()) {
                taskInterruptedCount = queue.size();
                queue.clear();
            }
        }
    }

    @Override
    public int getInterruptedTaskCount() {
        return taskInterruptedCount;
    }

    @Override
    //Если есть треды в состоянии RUNNABLE (кроме текущего) возвращаем false, иначе true
    public boolean isFinished() {
        for (NotifyingPoolWorker thread : threads) {
            if (thread.getId() == Thread.currentThread().getId())
                continue;
            if (thread.getState() == Thread.State.RUNNABLE)
                return false;
        }
        return true;
    }

//    private void printThreadState() {
//        System.err.println("\nThread states: ");
//            for (NotifyingPoolWorker thread : threads) {
//                System.err.println(thread.getState());
//            }
//    }
}
