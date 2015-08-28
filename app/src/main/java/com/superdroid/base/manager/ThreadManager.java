package com.superdroid.base.manager;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by GT on 2015/8/20.
 * 线程管理类
 */
public class ThreadManager {
    private static ThreadManager mThreadManager;

    private ThreadPoolExecutor longPoolExecutor;
    private ThreadPoolExecutor shortPoolExecutor;
    private Object longThreadLock = new Object();
    private Object shortThreadLock = new Object();

    public static ThreadManager getThreadManagerInstance() {
        if (mThreadManager == null) {
            mThreadManager = new ThreadManager();
        }
        return mThreadManager;
    }

    /**
     * 执行长（耗时）任务
     *
     * @param runnable
     */
    public void longTaskExecute(Runnable runnable) {
        synchronized (longThreadLock) {
            if (longPoolExecutor == null) {
                ThreadPoolProxy proxy = new ThreadPoolProxy(5, 6, 100);
                longPoolExecutor = proxy.createThreadPoolExecutor();
            }
        }
        longPoolExecutor.execute(runnable);
    }

    /**
     * 执行短任务
     *
     * @param runnable
     */
    public void shortTaskExecute(Runnable runnable) {
        synchronized (shortThreadLock) {
            if (shortPoolExecutor == null) {
                ThreadPoolProxy proxy = new ThreadPoolProxy(3, 5, 100);
                shortPoolExecutor = proxy.createThreadPoolExecutor();
            }
        }
        shortPoolExecutor.execute(runnable);
    }

    /**
     * 取消执行长（耗时）任务
     *
     * @param runnable
     */
    public void cancleLongTask(Runnable runnable) {
        if (longPoolExecutor == null || longPoolExecutor.isShutdown()
                || longPoolExecutor.isTerminated()) {
            return;
        } else {
            longPoolExecutor.getQueue().remove(runnable);
        }
    }

    /**
     * 取消执行短任务
     *
     * @param runnable
     */
    public void cancleShortTask(Runnable runnable) {
        if (shortPoolExecutor == null || shortPoolExecutor.isShutdown()
                || shortPoolExecutor.isTerminated()) {
            return;
        } else {
            shortPoolExecutor.getQueue().remove(runnable);
        }
    }


    /**
     * 线程池代理类
     */
    private class ThreadPoolProxy {
        //        corePoolSize - 池中所保存的线程数，包括空闲线程。
//        maximumPoolSize - 池中允许的最大线程数。
//        keepAliveTime - 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间。
//        unit - keepAliveTime 参数的时间单位。
//        workQueue - 执行前用于保持任务的队列。此队列仅保持由 execute 方法提交的 Runnable 任务。
//        threadFactory - 执行程序创建新线程时使用的工厂。
//        handler - 由于超出线程范围和队列容量而使执行被阻塞时所使用的处理程序。
        private ThreadPoolExecutor mThreadPoolExecutor;
        private int corePoolSize;
        private int maximumPoolSize;
        private long keepAliveTime;

        /**
         * 构造方法
         *
         * @param corePoolSize    线程池维护线程的最少数量
         * @param maximumPoolSize 线程池维护线程的最大数量
         * @param keepAliveTime   线程池维护线程所允许的空闲时间
         */
        public ThreadPoolProxy(int corePoolSize, int maximumPoolSize,
                               long keepAliveTime) {
            this.corePoolSize = corePoolSize;
            this.maximumPoolSize = maximumPoolSize;
            this.keepAliveTime = keepAliveTime;
        }

        /**
         * 创建线程池
         *
         * @return
         */
        public ThreadPoolExecutor createThreadPoolExecutor() {
            if (mThreadPoolExecutor == null) {
                mThreadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
                        maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
                        new LinkedBlockingQueue<Runnable>(5),
                        Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy());
            }
            return mThreadPoolExecutor;
        }
    }
}
